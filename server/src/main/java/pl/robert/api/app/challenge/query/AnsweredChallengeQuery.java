package pl.robert.api.app.challenge.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.question.query.AnsweredQuestionQuery;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnsweredChallengeQuery {

    List<AnsweredQuestionQuery> questions;
    List<Character> areCorrect;
    List<String> myAnswers;
}
