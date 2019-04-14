package pl.robert.api.app.vote.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.post.domain.Post;
import pl.robert.api.app.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(nullable = false)
    LocalDateTime date;

    @Column(nullable = false)
    char type;
}
