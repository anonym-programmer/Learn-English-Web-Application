package pl.robert.api.app.vote.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static pl.robert.api.app.user.domain.UserConstants.M_USERNAME_EMPTY;
import static pl.robert.api.app.vote.domain.VoteConstants.*;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateVoteDto {

    @NotEmpty(message = M_POST_ID_EMPTY)
    String postId;

    @NotEmpty(message = M_USERNAME_EMPTY)
    String username;

    @NotEmpty(message = M_TYPE_EMPTY)
    @Size(min = COL_LENGTH_MIN_TYPE, max = COL_LENGTH_MAX_TYPE)
    String type;
}
