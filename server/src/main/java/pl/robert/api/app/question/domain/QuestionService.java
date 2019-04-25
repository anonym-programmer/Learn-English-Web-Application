package pl.robert.api.app.question.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.challenge.domain.Challenge;
import pl.robert.api.app.question.query.QuestionQuery;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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

    List<QuestionQuery> queryQuestionsOfDefenderChallengeId(Challenge challenge) {
        return challenge.getQuestions()
                .stream()
                .map(question -> new QuestionQuery(
                        question.getId(),
                        question.getQuestion(),
                        question.getAnswers().split(":", -1)))
                .filter(distinctByKey(QuestionQuery::getQuestionId))
                .collect(Collectors.toList());
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    boolean areQuestionsExist(List<Long> questionsId) {
        return questionsId
                .stream()
                .noneMatch(questionId -> repository.findById(questionId).isEmpty());
    }

    List<Character> calculateCorrectAnswers(List<Character> answers, List<Long> questionsId) {
        List<Character> correctAnswers = questionsId
                .stream()
                .map(repository::findById)
                .collect(Collectors.toList())
                .stream()
                .filter(Optional::isPresent)
                .map(correctAnswer -> correctAnswer.get().getCorrectAnswerShortForm().toString().charAt(0))
                .collect(Collectors.toList());

        List<Character> myAnswers = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            if (correctAnswers.get(i) == answers.get(i)) myAnswers.add('1');
            else myAnswers.add('0');
        }

        return myAnswers;
    }

    List<Question> getQuestionsByIds(List<Long> questionsIds) {
        return List.copyOf(questionsIds
                .stream()
                .map(repository::findById)
                .collect(Collectors.toList())
                .stream()
                .map(Optional::get)
                .collect(Collectors.toList()));
    }
}
