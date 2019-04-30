package pl.robert.api.app.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static pl.robert.api.app.shared.Constants.COL_LENGTH_CHALLENGE_SCORE;
import static pl.robert.api.app.shared.Constants.COL_LENGTH_RANK;

@Entity
@Table(name = "user_details")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    String level;

    @Column(nullable = false)
    String expierience;

    @Column(name = "current_rank", length = COL_LENGTH_RANK, nullable = false)
    String currentRank;

    @Column(name = "number_of_wins", length = COL_LENGTH_CHALLENGE_SCORE, nullable = false)
    String numberOfWins;

    @Column(name = "number_of_loses", length = COL_LENGTH_CHALLENGE_SCORE, nullable = false)
    String numberOfLoses;

    @Column(name = "number_of_draws", length = COL_LENGTH_CHALLENGE_SCORE, nullable = false)
    String numberOfDraws;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @Transient
    String leftExperienceToTheNextLevel;

    @Transient
    String currentExperienceInPercents;
}
