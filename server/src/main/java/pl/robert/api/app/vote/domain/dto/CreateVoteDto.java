package pl.robert.api.app.vote.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static pl.robert.api.app.shared.Constants.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateVoteDto {

    @NotEmpty(message = M_POST_ID_EMPTY)
    String postId;

    String username;

    @NotEmpty(message = M_TYPE_EMPTY)
    @Size(min = COL_LENGTH_MIN_TYPE, max = COL_LENGTH_MAX_TYPE)
    String type;
}
