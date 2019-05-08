package pl.robert.api.app.comment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.comment.domain.dto.CreateCommentDto;
import pl.robert.api.app.comment.query.CommentQuery;
import pl.robert.api.app.post.domain.PostFacade;
import pl.robert.api.app.user.domain.UserFacade;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentFacade {

    CommentValidator validator;
    CommentService service;
    PostFacade postFacade;
    UserFacade userFacade;

    public void checkInputData(CreateCommentDto dto, BindingResult result) {
        if (!result.hasErrors()) validator.checkInputData(dto, result);
    }

    public void add(CreateCommentDto dto) {
        userFacade.incrementUserComments(dto.getUsername());
        userFacade.addExperience(dto.getUsername(), 20);
        service.saveAndFlush(CommentFactory.create(
                dto,
                postFacade.findPostById(Long.parseLong(dto.getPostId())),
                userFacade.findUserByUsername(dto.getUsername())
        ));
    }

    public List<CommentQuery> queryCommentsByPost(long postId) {
        return service.queryCommentsByPost(postFacade.findPostById(postId));
    }
}
