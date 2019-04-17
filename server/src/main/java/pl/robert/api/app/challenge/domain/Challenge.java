package pl.robert.api.app.challenge.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pl.robert.api.app.shared.Constants.COL_LENGTH_DATE;

@Entity
@Table(name = "challenges")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne
    Opponent attacker;

    @OneToOne
    Opponent defender;

    @Column(length = COL_LENGTH_DATE, nullable = false)
    LocalDateTime date;

    @ElementCollection
    List<Question> questions = new ArrayList<>(5);
}
