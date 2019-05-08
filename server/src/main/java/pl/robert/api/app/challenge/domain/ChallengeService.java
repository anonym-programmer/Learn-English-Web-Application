package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.challenge.domain.dto.CreateChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitPendingChallengeDto;
import pl.robert.api.app.challenge.query.*;
import pl.robert.api.app.opponent.Opponent;
import pl.robert.api.app.opponent.OpponentFacade;
import pl.robert.api.app.opponent.dto.CreateOpponentDto;
import pl.robert.api.app.question.domain.QuestionFacade;
import pl.robert.api.app.question.query.AnsweredQuestionQuery;
import pl.robert.api.app.question.query.QuestionQuery;
import pl.robert.api.app.user.domain.UserFacade;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ChallengeService {

    ChallengeRepository repository;
    OpponentFacade opponentFacade;
    QuestionFacade questionFacade;
    UserFacade userFacade;

    void submitChallenge(SubmitChallengeDto dto) {
        repository.saveAndFlush(ChallengeFactory.create(
                new CreateChallengeDto(
                        opponentFacade.add(
                                new CreateOpponentDto(
                                        transformAnswers(dto.getAnswers()),
                                        calculateCorrectAnswers(dto.getAnswers(), dto.getQuestionsIds()),
                                        userFacade.findUserByUsername(dto.getAttackerUsername())
                                )
                        ),
                        opponentFacade.add(
                                new CreateOpponentDto(
                                        userFacade.findUserByUsername(dto.getDefenderUsername())
                                )
                        ),
                        questionFacade.queryQuestionsByIds(dto.getQuestionsIds())
                )
        ));
    }

    void submitPendingChallenge(SubmitPendingChallengeDto dto) {
        Challenge challenge = repository.findById(Long.parseLong(dto.getChallengeId()));

        challenge.getDefender().setMyAnswers(transformAnswers(dto.getAnswers()));
        challenge.getDefender().setAnswersStatus(calculateCorrectAnswers(dto.getAnswers(), dto.getQuestionsIds()));

        opponentFacade.saveOpponentsAfterChallenge(challenge.getAttacker(), challenge.getDefender());

        challenge.setStatus(ChallengeStatus.COMPLETED);
        repository.save(challenge);
    }

    private String transformAnswers(List<Character> answers) {
        return answers.toString()
                .substring(1, 3 * answers.size() - 1)
                .replaceAll(", ", "")
                .replaceAll(".(?!$)", "$0:");
    }

    private String calculateCorrectAnswers(List<Character> answers, List<Long> questionsId) {
        return transformAnswers(questionFacade.calculateCorrectAnswers(answers, questionsId));
    }

    void delete(long id) {
        repository.delete(repository.findById(id));
    }

    boolean isChallengeNotExists(Long id) {
        return repository.findById(id).isEmpty();
    }

    List<SubmitedChallengeQuery> queryAttackerPendingChallenges(String username) {
        return List.copyOf(opponentFacade.queryIdsOfAttackerPendingChallenges(userFacade.findUserByUsername(username).getId())
                .stream()
                .map(repository::findByAttackerId)
                .collect(Collectors.toList())
                .stream()
                .map(challenge -> new SubmitedChallengeQuery(
                        challenge.getDefender().getUser().getUsername(),
                        String.valueOf(challenge.getDateOfCreation()).substring(0, 10),
                        String.valueOf(challenge.getDateOfCreation()).substring(11, 19)))
                .collect(Collectors.toList()));
    }

    List<PendingChallengeQuery> queryDefenderPendingChallenges(String username) {
        return List.copyOf(opponentFacade.queryIdsOfDefenderPendingChallenges(userFacade.findUserByUsername(username).getId())
                .stream()
                .map(repository::findByDefenderId)
                .collect(Collectors.toList())
                .stream()
                .map(challenge -> new PendingChallengeQuery(
                        String.valueOf(challenge.getId()),
                        challenge.getAttacker().getUser().getUsername(),
                        String.valueOf(challenge.getDateOfCreation()).substring(0, 10),
                        String.valueOf(challenge.getDateOfCreation()).substring(11, 19)))
                .collect(Collectors.toList()));
    }

    List<QuestionQuery> queryQuestionsOfDefenderChallengeId(String challengeId) {
        return questionFacade.queryQuestionsOfDefenderChallengeId(repository.findById(Long.parseLong(challengeId)));
    }

    List<CompletedChallengeQuery> queryCompletedChallenges(String username) {
        return List.copyOf(opponentFacade.queryIdsOfUserCompletedChallenges(userFacade.findUserByUsername(username).getId())
                .stream()
                .map(repository::findByDefenderOrAttackerId)
                .collect(Collectors.toList())
                .stream()
                .map(challenge -> new CompletedChallengeQuery(
                        String.valueOf(challenge.getId()),
                        challenge.getDefender().getUser().getUsername().equals(username) ?
                                challenge.getAttacker().getUser().getUsername() :
                                challenge.getDefender().getUser().getUsername(),
                        String.valueOf(challenge.getDateOfCreation()).substring(0, 10),
                        String.valueOf(challenge.getDateOfCreation()).substring(11, 19),
                        challenge.getDefender().getUser().getUsername().equals(username) ?
                                String.valueOf(challenge.getDefender().getResult()) :
                                String.valueOf(challenge.getAttacker().getResult())))
                .collect(Collectors.toList()));
    }

    CompletedChallengeDetailsQuery queryCompletedChallengeDetailsByChallengeId(String challengeId, String username) {
        Challenge challenge = repository.findById(Long.parseLong(challengeId));

        Opponent attacker = challenge.getAttacker();
        Opponent defender = challenge.getDefender();

        if (isUsernameEqualsAttackerUsername(username, challenge.getAttacker().getUser().getUsername())) {
            return CompletedChallengeDetailsQuery.builder()
                    .id(challengeId)
                    .result(opponentFacade.transformResult(String.valueOf(attacker.getResult())))
                    .score(String.valueOf(opponentFacade.countCorrectAnswers(String.valueOf(attacker.getAnswersStatus()))))
                    .opponentScore(String.valueOf(opponentFacade.countCorrectAnswers(String.valueOf(defender.getAnswersStatus()))))
                    .username(attacker.getUser().getUsername())
                    .opponentUsername(defender.getUser().getUsername())
                    .gainedExperienceForCorrectAnswers(attacker.getGainedExperienceForCorrectAnswers())
                    .bonusExperienceForResult(attacker.getBonusExperienceForResult())
                    .totalGainedExperience(attacker.getTotalGainedExperience())
                    .answersStatus(opponentFacade.transformAnswersStatus(attacker.getAnswersStatus()))
                    .opponentAnswersStatus(opponentFacade.transformAnswersStatus(defender.getAnswersStatus()))
                    .build();
        }

        return CompletedChallengeDetailsQuery.builder()
                .id(challengeId)
                .result(opponentFacade.transformResult(String.valueOf(defender.getResult())))
                .score(String.valueOf(opponentFacade.countCorrectAnswers(String.valueOf(defender.getAnswersStatus()))))
                .opponentScore(String.valueOf(opponentFacade.countCorrectAnswers(String.valueOf(attacker.getAnswersStatus()))))
                .username(defender.getUser().getUsername())
                .opponentUsername(attacker.getUser().getUsername())
                .gainedExperienceForCorrectAnswers(defender.getGainedExperienceForCorrectAnswers())
                .bonusExperienceForResult(defender.getBonusExperienceForResult())
                .totalGainedExperience(defender.getTotalGainedExperience())
                .answersStatus(opponentFacade.transformAnswersStatus(defender.getAnswersStatus()))
                .opponentAnswersStatus(opponentFacade.transformAnswersStatus(attacker.getAnswersStatus()))
                .build();
    }

    private boolean isUsernameEqualsAttackerUsername(String username, String attackerUsername) {
        return username.equals(attackerUsername);
    }

    AnsweredChallengeQuery queryCompletedChallengeDetailsCorrectAnswersByChallengeId(String challengeId, String username) {
        Challenge challenge = repository.findById(Long.parseLong(challengeId));

        if (isUsernameEqualsAttackerUsername(username, challenge.getAttacker().getUser().getUsername())) {

            return AnsweredChallengeQuery.builder()
                    .questions(challenge.getQuestions()
                            .stream()
                            .map(question -> new AnsweredQuestionQuery(
                                    question.getQuestion(),
                                    Arrays.asList(question.getAnswers().split(":", -1)),
                                    String.valueOf(question.getCorrectAnswerShortForm()).charAt(0)))
                            .filter(distinctByKey(AnsweredQuestionQuery::getQuestion))
                            .collect(Collectors.toList()))
                    .areCorrect(opponentFacade.transformAnswersStatus(challenge.getAttacker().getAnswersStatus()))
                    .myAnswers(Arrays.asList(challenge.getAttacker().getMyAnswers().split(":", -1)))
                    .build();
        }

        return AnsweredChallengeQuery.builder()
                .questions(challenge.getQuestions()
                        .stream()
                        .map(question -> new AnsweredQuestionQuery(
                                question.getQuestion(),
                                Arrays.asList(question.getAnswers().split(":", -1)),
                                String.valueOf(question.getCorrectAnswerShortForm()).charAt(0)))
                        .filter(distinctByKey(AnsweredQuestionQuery::getQuestion))
                        .collect(Collectors.toList()))
                .areCorrect(opponentFacade.transformAnswersStatus(challenge.getDefender().getAnswersStatus()))
                .myAnswers(Arrays.asList(challenge.getDefender().getMyAnswers().split(":", -1)))
                .build();
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
