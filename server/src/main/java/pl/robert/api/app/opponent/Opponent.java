package pl.robert.api.app.opponent;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import pl.robert.api.app.challenge.domain.Challenge;
import pl.robert.api.app.user.domain.User;

import javax.persistence.*;

import java.util.List;

import static pl.robert.api.app.shared.Constants.*;

@Entity
@Table(name = "oponents")
@Builder
@DynamicInsert
@DynamicUpdate
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

    @Column(name = "gained_experience_for_correct_answers", length = COL_LENGTH_GAINED_EXPERIENCE)
    String gainedExperienceForCorrectAnswers;

    @Column(name = "bonus_experience_for_result", length = COL_LENGTH_GAINED_EXPERIENCE)
    String bonusExperienceForResult;

    @Column(name = "total_gained_experience", length = COL_LENGTH_TOTAL_GAINED_EXPERIENCE)
    String totalGainedExperience;

    @Enumerated(EnumType.STRING)
    @Column(length = COL_LENGTH_CHALLENGE_RESULT, nullable = false)
    OpponentResult result;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @OneToMany(mappedBy = "attacker", cascade = CascadeType.REMOVE)
    List<Challenge> attackerChallenges;

    @OneToMany(mappedBy = "defender", cascade = CascadeType.REMOVE)
    List<Challenge> defenderChallenges;
}
