package pl.robert.api.app.post.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.comment.domain.Comment;
import pl.robert.api.app.user.domain.User;
import pl.robert.api.app.vote.domain.Vote;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static pl.robert.api.app.shared.Constants.*;

@Entity
@Table(name = "posts")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = COL_LENGTH_MAX_TITLE, nullable = false)
    String title;

    @Column(length = COL_LENGTH_MAX_DESCRIPTION, nullable = false)
    String description;

    @Column(length = COL_LENGTH_DATE, nullable = false)
    LocalDateTime date;

    @Column(name = "up_vote", nullable = false)
    int upVote;

    @Column(name = "down_vote", nullable = false)
    int downVote;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Vote> votes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Comment> comments;
}
