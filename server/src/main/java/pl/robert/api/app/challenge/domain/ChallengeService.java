package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.challenge.domain.dto.CreateChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitPendingChallengeDto;
import pl.robert.api.app.challenge.query.ChallengePendingQuery;
import pl.robert.api.app.challenge.query.ChallengeSubmitedQuery;
import pl.robert.api.app.challenge.query.CompletedChallengeDetailsQuery;
import pl.robert.api.app.challenge.query.CompletedChallengeQuery;
import pl.robert.api.app.opponent.Opponent;
import pl.robert.api.app.opponent.OpponentFacade;
import pl.robert.api.app.opponent.dto.CreateOpponentDto;
import pl.robert.api.app.question.domain.QuestionFacade;
import pl.robert.api.app.question.query.QuestionQuery;
import pl.robert.api.app.user.domain.UserFacade;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ChallengeService {

    ChallengeRepository repository;
    OpponentFacade opponentFacade;
    QuestionFacade questionFacade;
    UserFacade userFacade;

    void add(SubmitChallengeDto dto) {
        repository.saveAndFlush(ChallengeFactory.create(
                new CreateChallengeDto(
                        opponentFacade.addAndReturnOpponent(
                                new CreateOpponentDto(
                                        transformAnswers(dto.getAnswers()),
                                        calculateCorrectAnswers(dto.getAnswers(), dto.getQuestionsIds()),
                                        userFacade.findUserByUsername(dto.getAttackerUsername())
                                )
                        ),
                        opponentFacade.addAndReturnOpponent(
                                new CreateOpponentDto(
                                        userFacade.findUserByUsername(dto.getDefenderUsername())
                                )
                        ),
                        questionFacade.getQuestionsByIds(dto.getQuestionsIds())
                )
        ));
    }

    void submitPendingChallenge(SubmitPendingChallengeDto dto) {
        Challenge challenge = repository.findById(Long.parseLong(dto.getChallengeId()));

        challenge.getDefender().setMyAnswers(transformAnswers(dto.getAnswers()));
        challenge.getDefender().setAnswersStatus(calculateCorrectAnswers(dto.getAnswers(), dto.getQuestionsIds()));

        opponentFacade.updateAndSaveOpponentsAfterChallenge(challenge.getAttacker(), challenge.getDefender());

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

    List<ChallengeSubmitedQuery> queryAttackerPendingChallenges(String username) {
        return List.copyOf(opponentFacade.findIdsOfAttackerPendingChallenges(userFacade.findUserByUsername(username).getId())
                .stream()
                .map(repository::findByAttackerId)
                .collect(Collectors.toList())
                .stream()
                .map(challenge -> new ChallengeSubmitedQuery(
                        challenge.getDefender().getUser().getUsername(),
                        String.valueOf(challenge.getDateOfCreation()).substring(0, 10),
                        String.valueOf(challenge.getDateOfCreation()).substring(11, 19)))
                .collect(Collectors.toList()));
    }

    List<ChallengePendingQuery> queryDefenderPendingChallenges(String username) {
        return List.copyOf(opponentFacade.findIdsOfDefenderPendingChallenges(userFacade.findUserByUsername(username).getId())
                .stream()
                .map(repository::findByDefenderId)
                .collect(Collectors.toList())
                .stream()
                .map(challenge -> new ChallengePendingQuery(
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
        return List.copyOf(opponentFacade.findIdsOfUserCompletedChallenges(userFacade.findUserByUsername(username).getId())
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
                    .gainedXP(attacker.getGainedXP())
                    .answersStatus(attacker.getAnswersStatus())
                    .build();

        }

        return CompletedChallengeDetailsQuery.builder()
                .id(challengeId)
                .result(opponentFacade.transformResult(String.valueOf(defender.getResult())))
                .score(String.valueOf(opponentFacade.countCorrectAnswers(String.valueOf(defender.getAnswersStatus()))))
                .opponentScore(String.valueOf(opponentFacade.countCorrectAnswers(String.valueOf(attacker.getAnswersStatus()))))
                .username(defender.getUser().getUsername())
                .opponentUsername(attacker.getUser().getUsername())
                .gainedXP(defender.getGainedXP())
                .answersStatus(defender.getAnswersStatus())
                .build();
    }

    private boolean isUsernameEqualsAttackerUsername(String username, String attackerUsername) {
        return username.equals(attackerUsername);
    }
}
