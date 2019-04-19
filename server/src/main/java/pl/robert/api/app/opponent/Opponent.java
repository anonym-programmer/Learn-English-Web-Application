package pl.robert.api.app.opponent;

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

    @Column(name = "my_answers", length = COL_LENGTH_MY_ANSWERS)
    String myAnswers;

    @Column(name = "answers_status", length = COL_LENGTH_MY_ANSWERS)
    String answersStatus;

    @Column(name = "gained_xp", length = COL_LENGTH_GAINED_XP)
    String gainedXP;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = COL_LENGTH_CHALLENGE_RESULT)
    OpponentResult result;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
