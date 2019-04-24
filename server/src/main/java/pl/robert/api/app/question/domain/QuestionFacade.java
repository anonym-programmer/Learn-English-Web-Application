package pl.robert.api.app.question.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.challenge.domain.Challenge;
import pl.robert.api.app.question.query.QuestionQuery;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionFacade {

    QuestionService service;

    public List<QuestionQuery> getRandomQuestions() {
        return service.getRandomQuestions();
    }

    public boolean areQuestionsExist(List<Long> questionsId) {
        return service.areQuestionsExist(questionsId);
    }

    public char[] calculateCorrectAnswers(char[] answers, List<Long> questionsId) {
        return service.calculateCorrectAnswers(answers, questionsId);
    }

    public List<Question> getQuestionsByIds(List<Long> questionsIds) {
        return service.getQuestionsByIds(questionsIds);
    }

    public List<QuestionQuery> queryQuestionsOfDefenderChallengeId(Challenge challenge) {
        return service.queryQuestionsOfDefenderChallengeId(challenge);
    }
}
