package pl.robert.api.app.challenge.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import pl.robert.api.app.opponent.Opponent;
import pl.robert.api.app.question.domain.Question;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pl.robert.api.app.shared.Constants.COL_LENGTH_CHALLENGE_STATUS;
import static pl.robert.api.app.shared.Constants.COL_LENGTH_DATE;

@Entity
@Table(name = "challenges")
@Builder
@DynamicUpdate
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "date_of_creation", length = COL_LENGTH_DATE, nullable = false)
    LocalDateTime dateOfCreation;

    @Enumerated(EnumType.STRING)
    @Column(length = COL_LENGTH_CHALLENGE_STATUS, nullable = false)
    ChallengeStatus status;

    @OneToOne(cascade = CascadeType.REMOVE)
    Opponent attacker;

    @OneToOne(cascade = CascadeType.REMOVE)
    Opponent defender;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Question> questions = new ArrayList<>(5);
}
