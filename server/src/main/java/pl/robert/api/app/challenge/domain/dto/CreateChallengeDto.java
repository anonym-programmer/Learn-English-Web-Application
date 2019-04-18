package pl.robert.api.app.challenge.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateChallengeDto {

    String myUsername;
    String opponentUsername;
    final Long[] questionsIds = new Long[5];
    final Character[] answers = new Character[4];
}
