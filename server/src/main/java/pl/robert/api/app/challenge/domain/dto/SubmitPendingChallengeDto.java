package pl.robert.api.app.challenge.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

import static pl.robert.api.app.shared.Constants.M_CHALLENGE_ID_EMPTY;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubmitPendingChallengeDto {

    @NotEmpty(message = M_CHALLENGE_ID_EMPTY)
    String challengeId;

    String defenderUsername;

    final List<Long> questionsIds = new ArrayList<>(5);

    char[] answers = new char[5];
}
