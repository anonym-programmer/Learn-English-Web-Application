package pl.robert.api.app.post.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.post.domain.PostConstants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePostDto implements PostConstants {

    @NotEmpty(message = M_TITLE_EMPTY)
    @Size(min = COL_LENGTH_MIN_TITLE, max = COL_LENGTH_MAX_TITLE, message = M_TITLE_LENGTH)
    String title;

    @NotEmpty(message = M_DESCRIPTION_EMPTY)
    @Size(min = COL_LENGTH_MIN_DESCRIPTION, max = COL_LENGTH_MAX_DESCRIPTION, message = M_DESCRIPTION_LENGTH)
    String description;
}
