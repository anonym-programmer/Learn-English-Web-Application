package pl.robert.api.app.comment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.comment.domain.dto.CreateCommentDto;
import pl.robert.api.app.post.domain.PostFacade;
import pl.robert.api.app.user.domain.UserFacade;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentFacade {

    CommentValidator validator;
    CommentService commentService;
    PostFacade postFacade;
    UserFacade userFacade;

    public void checkInputData(CreateCommentDto dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void add(CreateCommentDto dto) {
        Comment comment = CommentFactory.create(
                dto,
                postFacade.findPostById(Long.parseLong(dto.getPostId())),
                userFacade.findUserByUsername(dto.getUsername())
        );
        commentService.saveAndFlush(comment);
    }
}
