package pl.robert.api.app.challenge.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.user.domain.User;

import javax.persistence.*;

import static pl.robert.api.app.shared.Constants.*;

@Entity
@Table(name = "oponents")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Opponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String score;

    String gained_xp;

    Result result;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
