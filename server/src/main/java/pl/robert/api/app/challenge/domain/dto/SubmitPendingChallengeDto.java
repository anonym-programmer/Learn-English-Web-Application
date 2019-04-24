package pl.robert.api.app.challenge.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) @ToString
public class SubmitPendingChallengeDto {

    String challengeId;

    String defenderUsername;

    final List<Long> questionsIds = new ArrayList<>(5);

    char[] answers = new char[5];
}
