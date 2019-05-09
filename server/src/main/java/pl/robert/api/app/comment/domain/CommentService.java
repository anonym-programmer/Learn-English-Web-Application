package pl.robert.api.app.comment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.comment.query.CommentQuery;
import pl.robert.api.app.post.domain.Post;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CommentService {

    CommentRepository repository;

    void saveAndFlush(Comment comment) {
        repository.saveAndFlush(comment);
    }

    List<CommentQuery> queryCommentsByPost(Post post) {
        return repository.findAllByPost(post).stream()
                .map(comment -> new CommentQuery(
                        comment.getUser().getUsername(),
                        String.valueOf(comment.getDate()).substring(0, 10),
                        String.valueOf(comment.getDate()).substring(11, 19),
                        comment.getText()))
                .collect(Collectors.toList());
    }
}
