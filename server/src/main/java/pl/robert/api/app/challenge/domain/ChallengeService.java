package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.challenge.domain.dto.CreateChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitPendingChallengeDto;
import pl.robert.api.app.challenge.query.ChallengePendingQuery;
import pl.robert.api.app.challenge.query.ChallengeSubmitedQuery;
import pl.robert.api.app.opponent.OpponentFacade;
import pl.robert.api.app.opponent.OpponentResult;
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

        int attackerCorrectAnswers = countCorrectAnswers(challenge.getAttacker().getAnswersStatus());
        int defenderCorrectAnswers = countCorrectAnswers(challenge.getDefender().getAnswersStatus());

        if (attackerCorrectAnswers > defenderCorrectAnswers) {
            challenge.getAttacker().setResult(OpponentResult.WIN);
            challenge.getDefender().setResult(OpponentResult.LOSE);
        } else if (attackerCorrectAnswers < defenderCorrectAnswers) {
            challenge.getAttacker().setResult(OpponentResult.LOSE);
            challenge.getDefender().setResult(OpponentResult.WIN);
        } else {
            challenge.getAttacker().setResult(OpponentResult.DRAW);
            challenge.getDefender().setResult(OpponentResult.DRAW);
        }

        challenge.getAttacker().setGainedXP(String.valueOf(attackerCorrectAnswers * 15));
        challenge.getDefender().setGainedXP(String.valueOf(defenderCorrectAnswers * 15));

        opponentFacade.saveOpponent(challenge.getAttacker());
        opponentFacade.saveOpponent(challenge.getDefender());

        challenge.setStatus(ChallengeStatus.COMPLETED);
        repository.save(challenge);
    }

    private String transformAnswers(char[] answers) {
        return new String(answers).replaceAll(".(?!$)", "$0:");
    }

    private String calculateCorrectAnswers(char[] answers, List<Long> questionsId) {
        return transformAnswers(questionFacade.calculateCorrectAnswers(answers, questionsId));
    }

    private int countCorrectAnswers(String answerStatus) {
        return answerStatus.length() - answerStatus.replace("1", "").length();
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
}


























