package pl.robert.api.app.challenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class QuestionService {

    QuestionRepository repository;

    List<String> getRandomQuestions() {
        List<String> questions = new ArrayList<>();
        List<Integer> ids = new ArrayList<>(5);
        Random random = new Random();

        while (questions.size() != 5) {
            int randomId = random.nextInt((int) repository.count() + 1);
            if (randomId == 0) randomId = 1;
            if (!ids.contains(randomId)) {
                ids.add(randomId);
                questions.add(repository.findById(randomId).getQuestion());
            }
        }

        return questions;
    }
}
