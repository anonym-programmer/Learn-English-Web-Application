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

    public Opponent add(CreateOpponentDto dto) {
        return service.add(dto);
    }

    public void saveOpponentsAfterChallenge(Opponent attacker, Opponent defender) {
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

    public List<Long> queryIdsOfAttackerPendingChallenges(long attackerId) {
        return service.queryIdsOfAttackerPendingChallenges(attackerId);
    }

    public List<Long> queryIdsOfDefenderPendingChallenges(long defenderId) {
        return service.queryIdsOfDefenderPendingChallenges(defenderId);
    }

    public List<Long> queryIdsOfUserCompletedChallenges(long userId) {
        return service.queryIdsOfUserCompletedChallenges(userId);
    }
}
