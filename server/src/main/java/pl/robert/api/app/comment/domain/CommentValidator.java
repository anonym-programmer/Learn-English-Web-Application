package pl.robert.api.app.comment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.comment.domain.dto.CreateCommentDto;
import pl.robert.api.app.post.domain.PostFacade;
import pl.robert.api.app.user.domain.UserFacade;

import static pl.robert.api.app.shared.Constants.*;
import static pl.robert.api.app.shared.Constants.M_USERNAME_NOT_EXISTS;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CommentValidator {

    PostFacade postFacade;
    UserFacade userFacade;

    void checkInputData(CreateCommentDto dto, BindingResult result) {

        if (!postFacade.isPostExists(Long.parseLong(dto.getPostId()))) {
            result.rejectValue(F_POST_ID, C_NOT_EXISTS, M_POST_NOT_EXISTS);
        }

        if (!userFacade.isUserExists(dto.getUsername())) {
            result.rejectValue(F_USERNAME, C_NOT_EXISTS, M_USERNAME_NOT_EXISTS);
        }
    }
}
