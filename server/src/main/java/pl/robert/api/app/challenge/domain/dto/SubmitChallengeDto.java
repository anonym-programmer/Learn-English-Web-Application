package pl.robert.api.app.challenge.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import static pl.robert.api.app.shared.Constants.M_DEFENDER_USERNAME_EMPTY;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubmitChallengeDto {

    String attackerUsername;

    @NotEmpty(message = M_DEFENDER_USERNAME_EMPTY)
    String defenderUsername;

    final List<Long> questionsIds = new ArrayList<>();

    char[] answers = new char[5];
}
