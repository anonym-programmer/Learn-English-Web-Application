package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.challenge.domain.dto.ChooseChallengeOponentDto;
import pl.robert.api.app.user.domain.UserFacade;

import static pl.robert.api.app.shared.Constants.*;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ChallengeValidator {

    UserFacade userFacade;

    void checkInputData(ChooseChallengeOponentDto dto, BindingResult result) {

        if (!userFacade.isUserExists(dto.getAttackerUsername())) {
            result.rejectValue(F_ATTACKER_USERNAME, C_NOT_EXISTS, M_ATTACKER_USERNAME_NOT_EXISTS);
        }

        if (!userFacade.isUserExists(dto.getDefenderUsername())) {
            result.rejectValue(F_DEFENDER_USERNAME, C_NOT_EXISTS, M_DEFENDER_USERNAME_NOT_EXISTS);
        }
    }
}
