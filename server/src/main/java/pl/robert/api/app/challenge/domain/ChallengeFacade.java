package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.challenge.domain.dto.CreateChallengeDto;
import pl.robert.api.app.challenge.query.QuestionQuery;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChallengeFacade {

    ChallengeValidator validator;
    ChallengeService challengeService;
    QuestionService questionService;

    public void checkInputData(CreateChallengeDto dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public List<QuestionQuery> getRandomQuestions() {
        return questionService.getRandomQuestions();
    }
}
