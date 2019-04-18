package pl.robert.api.app.question.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.question.query.QuestionQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class QuestionService {

    QuestionRepository repository;

    List<QuestionQuery> getRandomQuestions() {
        List<QuestionQuery> questions = new ArrayList<>();
        List<Integer> ids = new ArrayList<>(5);
        Random random = new Random();

        while (questions.size() != 5) {
            int randomId = random.nextInt((int) repository.count() + 1);
            if (randomId == 0) randomId = 1;
            if (!ids.contains(randomId)) {
                ids.add(randomId);
                Question question = repository.findById(randomId);
                questions.add(new QuestionQuery(question.getId(), question.getQuestion(), question.getAnswers().split(":", -1)));
            }
        }

        return questions;
    }

    boolean areQuestionsExist(long[] questions) {
        for (long questionId: questions) {
            if (repository.findById(questionId) == null) {
                return false;
            }
        }
        return true;
    }
}
