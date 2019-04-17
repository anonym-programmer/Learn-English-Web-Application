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

    @Column(name = "number_of_correct_answers", nullable = false)
    char numberOfCorrectAnswers;

    @Column(name = "gained_xp", length = COL_LENGTH_GAINED_XP)
    String gainedXP;

    @Enumerated(EnumType.STRING)
    @Column(length = COL_LENGTH_RESULT)
    Result result;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
