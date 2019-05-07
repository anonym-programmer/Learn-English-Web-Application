package pl.robert.api.app.post.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static pl.robert.api.app.shared.Constants.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePostDto {

    @NotEmpty(message = M_TITLE_EMPTY)
    @Size(min = COL_LENGTH_MIN_TITLE, max = COL_LENGTH_MAX_TITLE, message = M_TITLE_LENGTH)
    String title;

    @NotEmpty(message = M_DESCRIPTION_EMPTY)
    @Size(min = COL_LENGTH_MIN_DESCRIPTION, max = COL_LENGTH_MAX_DESCRIPTION, message = M_DESCRIPTION_LENGTH)
    String description;
}
