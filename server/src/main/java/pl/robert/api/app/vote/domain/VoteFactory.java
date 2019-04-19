package pl.robert.api.app.vote.domain;

import pl.robert.api.app.post.domain.Post;
import pl.robert.api.app.user.domain.User;
import pl.robert.api.app.vote.domain.dto.CreateVoteDto;

import java.time.LocalDateTime;

class VoteFactory {

    static Vote create(CreateVoteDto dto, Post post, User user) {
        return Vote.builder()
                .post(post)
                .user(user)
                .date(LocalDateTime.now())
                .type(dto.getType().toUpperCase().charAt(0))
                .build();
    }
}
