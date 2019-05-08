package pl.robert.api.app.opponent;

import com.google.common.primitives.Chars;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.opponent.dto.CreateOpponentDto;
import pl.robert.api.app.user.domain.UserFacade;

import java.util.List;

import static pl.robert.api.app.shared.Constants.*;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class OpponentService {

    OpponentRepository repository;
    UserFacade userFacade;

    Opponent addOpponent(CreateOpponentDto dto) {
        return repository.save(OpponentFactory.create(dto));
    }

    List<Long> queryIdsOfAttackerPendingChallenges(long attackerId) {
        return repository.findIdsOfAttackerPendingChallenges(attackerId);
    }

    List<Long> queryIdsOfDefenderPendingChallenges(long defenderId) {
        return repository.findIdsOfDefenderPendingChallenges(defenderId);
    }

    List<Long> queryIdsOfUserCompletedChallenges(long userId) {
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

        calculateStatistics(attacker, attacker.getResult(), attackerCorrectAnswers);
        calculateStatistics(defender, defender.getResult(), defenderCorrectAnswers);
    }

    private void calculateStatistics(Opponent opponent, OpponentResult result, int numOfCorrectAnswers) {
        switch (result) {
            case WIN:
                opponent.setBonusExperienceForResult(String.valueOf(numOfCorrectAnswers * 15));
                userFacade.incrementUserWins(opponent.getUser().getUsername());
                break;
            case LOSE:
                opponent.setBonusExperienceForResult(String.valueOf(1));
                userFacade.incrementUserLoses(opponent.getUser().getUsername());
                break;
            case DRAW:
                opponent.setBonusExperienceForResult(String.valueOf(10));
                userFacade.incrementUserDraws(opponent.getUser().getUsername());
                break;
        }
        opponent.setGainedExperienceForCorrectAnswers(String.valueOf(numOfCorrectAnswers * 15));
        opponent.setTotalGainedExperience(
                String.valueOf(
                        Integer.parseInt(opponent.getGainedExperienceForCorrectAnswers()) +
                                Integer.parseInt(opponent.getBonusExperienceForResult())
                )
        );
        userFacade.addExperience(opponent.getUser().getUsername(), Integer.parseInt(opponent.getTotalGainedExperience()));
        repository.save(opponent);
    }

    int countCorrectAnswers(String answerStatus) {
        return answerStatus.length() - answerStatus.replace("1", "").length();
    }

    String transformResult(String result) {
        switch (result) {
            case F_CHALLENGE_RESULT_WIN:
                return M_CHALLENGE_RESULT_WIN;
            case F_CHALLENGE_RESULT_LOSE:
                return M_CHALLENGE_RESULT_LOSE;
            case F_CHALLENGE_RESULT_DRAW:
                return M_CHALLENGE_RESULT_DRAW;
            default:
                return M_CHALLENGE_RESULT_NONE;
        }
    }

    List<Character> transformAnswersStatus(String answersStatus) {
        return Chars.asList(answersStatus.replaceAll("[^10]", "").toCharArray());
    }
}
