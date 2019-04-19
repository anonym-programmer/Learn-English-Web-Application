package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.challenge.domain.dto.ChooseChallengeOponentDto;
import pl.robert.api.app.challenge.domain.dto.DeleteChallengeDto;
import pl.robert.api.app.challenge.domain.dto.MakeChallengeDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChallengeFacade {

    ChallengeValidator validator;
    ChallengeService service;

    public void checkInputData(ChooseChallengeOponentDto dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void checkInputData(MakeChallengeDto dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void add(MakeChallengeDto dto) {
        service.add(dto);
    }

    public boolean isInputDataCorrect(DeleteChallengeDto dto) {
        return validator.isInputDataCorrect(dto);
    }

    public void delete(long id) {
        service.delete(id);
    }
}
