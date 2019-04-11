package pl.robert.api.app.comment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.robert.api.app.post.domain.Post;

import java.util.List;

interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);
}
