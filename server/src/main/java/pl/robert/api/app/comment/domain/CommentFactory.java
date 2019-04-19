package pl.robert.api.app.comment.domain;

import pl.robert.api.app.comment.domain.dto.CreateCommentDto;
import pl.robert.api.app.post.domain.Post;
import pl.robert.api.app.user.domain.User;

import java.time.LocalDateTime;

class CommentFactory {

    static Comment create(CreateCommentDto dto, Post post, User user) {
        return Comment.builder()
                .post(post)
                .user(user)
                .text(dto.getText())
                .date(LocalDateTime.now())
                .build();
    }
}
