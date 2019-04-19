package pl.robert.api.app.question.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.question.query.QuestionQuery;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionFacade {

    QuestionService service;

    public List<QuestionQuery> getRandomQuestions() {
        return service.getRandomQuestions();
    }

    public boolean areQuestionsExist(long[] questionsId) {
        return service.areQuestionsExist(questionsId);
    }

    public char[] calculateCorrectAnswers(char[] answers, long[] questionsId) {
        return service.calculateCorrectAnswers(answers, questionsId);
    }
}
