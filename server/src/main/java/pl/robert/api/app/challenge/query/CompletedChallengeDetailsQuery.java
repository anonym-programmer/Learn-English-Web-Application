package pl.robert.api.app.challenge.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompletedChallengeDetailsQuery {

    String id;
    String result;
    String score;
    String opponentScore;
    String username;
    String opponentUsername;
    String gainedExperienceForCorrectAnswers;
    String bonusExperienceForResult;
    String totalGainedExperience;
    List<Character> answersStatus;
    List<Character> opponentAnswersStatus;
}
