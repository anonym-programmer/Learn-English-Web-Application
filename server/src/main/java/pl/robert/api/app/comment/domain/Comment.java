package pl.robert.api.app.comment.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.post.domain.Post;
import pl.robert.api.app.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

import static pl.robert.api.app.shared.Constants.COL_LENGTH_MAX_TEXT;

@Entity
@Table(name = "comments")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = COL_LENGTH_MAX_TEXT, nullable = false)
    String text;

    @Column(nullable = false)
    LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
}
