package pl.robert.api.app.post.domain;

import pl.robert.api.app.post.domain.dto.CreatePostDto;
import pl.robert.api.app.user.domain.User;

import java.time.LocalDateTime;

class PostFactory {

    static Post create(CreatePostDto dto, User user) {
        return Post.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .date(LocalDateTime.now())
                .upVote(0)
                .downVote(0)
                .user(user)
                .build();
    }
}
