package pl.robert.api.app.post.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

import static pl.robert.api.app.post.domain.PostConstants.COL_LENGTH_DATE;
import static pl.robert.api.app.post.domain.PostConstants.COL_LENGTH_MAX_TITLE;

@Entity
@Table(name = "posts")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = COL_LENGTH_MAX_TITLE, nullable = false)
    String title;

    @Column(nullable = false)
    String description;

    @Column(length = COL_LENGTH_DATE, nullable = false)
    LocalDateTime date;

    @Column(nullable = false)
    int upVote;

    @Column(nullable = false)
    int downVote;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    User user;
}
