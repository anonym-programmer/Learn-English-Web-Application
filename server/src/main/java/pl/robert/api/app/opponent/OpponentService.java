package pl.robert.api.app.opponent;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.opponent.dto.CreateOpponentDto;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class OpponentService {

    OpponentRepository repository;

    Opponent addOpponent(CreateOpponentDto dto) {
        return repository.save(OpponentFactory.create(dto));
    }

    List<Long> findIdsOfAttackerPendingChallenges(long attackerId) {
        return repository.findIdsOfAttackerPendingChallenges(attackerId);
    }

    List<Long> findIdsOfDefenderPendingChallenges(long defenderId) {
        return repository.findIdsOfDefenderPendingChallenges(defenderId);
    }

    List<Long> findIdsOfUserCompletedChallenges(long userId) {
        return repository.findIdsOfUserCompletedChallenges(userId);
    }

    void updateAndSaveOpponentsAfterChallenge(Opponent attacker, Opponent defender) {
        int attackerCorrectAnswers = countCorrectAnswers(attacker.getAnswersStatus());
        int defenderCorrectAnswers = countCorrectAnswers(defender.getAnswersStatus());

        if (attackerCorrectAnswers > defenderCorrectAnswers) {
            attacker.setResult(OpponentResult.WIN);
            defender.setResult(OpponentResult.LOSE);
        } else if (attackerCorrectAnswers < defenderCorrectAnswers) {
            attacker.setResult(OpponentResult.LOSE);
            defender.setResult(OpponentResult.WIN);
        } else {
            attacker.setResult(OpponentResult.DRAW);
            defender.setResult(OpponentResult.DRAW);
        }

        attacker.setGainedXP(String.valueOf(attackerCorrectAnswers * 15));
        defender.setGainedXP(String.valueOf(defenderCorrectAnswers * 15));

        repository.save(attacker);
        repository.save(defender);
    }

    private int countCorrectAnswers(String answerStatus) {
        return answerStatus.length() - answerStatus.replace("1", "").length();
    }
}
