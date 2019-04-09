package pl.robert.api.app.vote.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.robert.api.app.post.domain.Post;
import pl.robert.api.app.user.domain.User;

interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findByPostAndUser(Post post, User user);
}
