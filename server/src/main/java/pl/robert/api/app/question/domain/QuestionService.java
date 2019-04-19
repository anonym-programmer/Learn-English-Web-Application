package pl.robert.api.app.question.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.question.query.QuestionQuery;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class QuestionService {

    QuestionRepository repository;

    List<QuestionQuery> getRandomQuestions() {
        List<Question> questionList = repository
                .findAll()
                .subList(0, (int) repository.count());
        Collections.shuffle(questionList);

        return List.copyOf(questionList
                .stream()
                .map(question -> new QuestionQuery(
                        question.getId(),
                        question.getQuestion(),
                        question.getAnswers().split(":", -1)))
                .collect(Collectors.toList())
                .subList(0, 5));
    }

    boolean areQuestionsExist(long[] questionsId) {
        for (long questionId : questionsId) {
            if (repository.findById(questionId) == null) {
                return false;
            }
        }
        return true;
    }

    char[] calculateCorrectAnswers(char[] answers, long[] questionsId) {
        char[] correctAnswers = new char[5];
        for (int i=0; i<5; i++) {
            correctAnswers[i] = repository.findById(questionsId[i]).getCorrectAnswerShortForm().toString().charAt(0);
        }

        char[] myAnswers = new char[5];
        for (int i=0; i<5; i++) {
            if (correctAnswers[i] == answers[i]) {
                myAnswers[i] = '1';
            } else {
                myAnswers[i] = '0';
            }
        }

        return myAnswers;
    }

    List<Question> getQuestionsByIds(long[] questionsIds) {
        List<Question> questions = new ArrayList<>();
        for (long questionId : questionsIds) {
            questions.add(repository.findById(questionId));
        }
        return questions;
    }
}
