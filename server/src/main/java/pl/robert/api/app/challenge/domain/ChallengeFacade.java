package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.challenge.domain.dto.MakeChallengeDto;
import pl.robert.api.app.challenge.domain.dto.DeleteChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitChallengeDto;
import pl.robert.api.app.challenge.query.ChallengePendingQuery;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChallengeFacade {

    ChallengeValidator validator;
    ChallengeService service;

    public void checkInputData(MakeChallengeDto dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void checkInputData(SubmitChallengeDto dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void add(SubmitChallengeDto dto) {
        service.add(dto);
    }

    public boolean isInputDataCorrect(DeleteChallengeDto dto) {
        return validator.isInputDataCorrect(dto);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public List<ChallengePendingQuery> queryAttackerPendingChallenges(String username) {
        return service.queryAttackerPendingChallenges(username);
    }

    public List<ChallengePendingQuery> queryDefenderPendingChallenges(String username) {
        return service.queryDefenderPendingChallenges(username);
    }
}
