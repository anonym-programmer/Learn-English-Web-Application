package pl.robert.api.app.challenge.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.opponent.Opponent;
import pl.robert.api.app.question.domain.Question;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateChallengeDto {

    Opponent attacker;
    Opponent defender;
    List<Question> questions;
}
