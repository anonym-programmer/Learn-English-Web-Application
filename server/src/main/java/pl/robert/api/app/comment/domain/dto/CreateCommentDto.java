package pl.robert.api.app.comment.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static pl.robert.api.app.shared.Constants.*;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCommentDto {

    @NotEmpty(message = M_POST_ID_EMPTY)
    String postId;

    String username;

    @NotEmpty(message = M_TEXT_EMPTY)
    @Size(min = COL_LENGTH_MIN_TEXT, max = COL_LENGTH_MAX_TEXT, message = M_COMMENT_LENGTH)
    String text;
}
