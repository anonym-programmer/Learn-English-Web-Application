package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.challenge.domain.dto.MakeChallengeDto;
import pl.robert.api.app.challenge.domain.dto.DeleteChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitPendingChallengeDto;
import pl.robert.api.app.challenge.query.ChallengePendingQuery;
import pl.robert.api.app.challenge.query.ChallengeSubmitedQuery;
import pl.robert.api.app.question.query.QuestionQuery;

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

    public void submitPendingChallenge(SubmitPendingChallengeDto dto) {
        service.submitPendingChallenge(dto);
    }

    public boolean isInputDataCorrect(DeleteChallengeDto dto) {
        return validator.isInputDataCorrect(dto);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public List<ChallengeSubmitedQuery> queryAttackerPendingChallenges(String username) {
        return service.queryAttackerPendingChallenges(username);
    }

    public List<ChallengePendingQuery> queryDefenderPendingChallenges(String username) {
        return service.queryDefenderPendingChallenges(username);
    }

    public List<QuestionQuery> queryQuestionsOfDefenderChallengeId(String challengeId) {
        return service.queryQuestionsOfDefenderChallengeId(challengeId);
    }
}
