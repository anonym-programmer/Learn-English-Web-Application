package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.challenge.domain.dto.ChooseChallengeOponentDto;
import pl.robert.api.app.challenge.domain.dto.CreateChallengeDto;
import pl.robert.api.app.question.domain.QuestionFacade;
import pl.robert.api.app.user.domain.UserFacade;

import static pl.robert.api.app.shared.Constants.*;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ChallengeValidator {

    UserFacade userFacade;
    QuestionFacade questionFacade;

    void checkInputData(ChooseChallengeOponentDto dto, BindingResult result) {

        areUsersCorrect(dto.getAttackerUsername(), dto.getDefenderUsername(), result);
    }

    void checkInputData(CreateChallengeDto dto, BindingResult result) {

        areUsersCorrect(dto.getAttackerUsername(), dto.getDefenderUsername(), result);

        if (!questionFacade.areQuestionsExist(dto.getQuestionsIds())) {
            result.rejectValue(F_QUESTION_IDS, C_NOT_EXISTS, M_QUESTION_IDS_NOT_EXISTS);
        }
    }

    private void areUsersCorrect(String attackerUsername, String defenderUsername, BindingResult result) {

        if (userFacade.isUsernameNotExists(attackerUsername)) {
            result.rejectValue(F_ATTACKER_USERNAME, C_NOT_EXISTS, M_ATTACKER_USERNAME_NOT_EXISTS);
        }

        if (userFacade.isUsernameNotExists(defenderUsername)) {
            result.rejectValue(F_DEFENDER_USERNAME, C_NOT_EXISTS, M_DEFENDER_USERNAME_NOT_EXISTS);
        }

        if (attackerUsername.equals(defenderUsername)) {
            result.rejectValue(F_DEFENDER_USERNAME, C_MATCH, M_ATTACKER_EQUALS_DEFENDER_USERNAME);
        }
    }
}
