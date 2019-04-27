package pl.robert.api.app.opponent;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.opponent.dto.CreateOpponentDto;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OpponentFacade {

    OpponentService service;

    public Opponent addAndReturnOpponent(CreateOpponentDto dto) {
        return service.addOpponent(dto);
    }

    public List<Long> findIdsOfAttackerPendingChallenges(long attackerId) {
        return service.findIdsOfAttackerPendingChallenges(attackerId);
    }

    public List<Long> findIdsOfDefenderPendingChallenges(long defenderId) {
        return service.findIdsOfDefenderPendingChallenges(defenderId);
    }

    public List<Long> findIdsOfUserCompletedChallenges(long userId) {
        return service.findIdsOfUserCompletedChallenges(userId);
    }

    public void updateAndSaveOpponentsAfterChallenge(Opponent attacker, Opponent defender) {
        service.updateAndSaveOpponentsAfterChallenge(attacker, defender);
    }

    public String transformResult(String result) {
        return service.transformResult(result);
    }

    public int countCorrectAnswers(String answerStatus) {
        return service.countCorrectAnswers(answerStatus);
    }

    public List<Character> transformAnswersStatus(String answersStatus) {
        return service.transformAnswersStatus(answersStatus);
    }
}
