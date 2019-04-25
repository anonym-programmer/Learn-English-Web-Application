package pl.robert.api.app.challenge.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompletedChallengeQuery {

    String id;
    String opponentName;
    String date;
    String time;
    String result;
}
