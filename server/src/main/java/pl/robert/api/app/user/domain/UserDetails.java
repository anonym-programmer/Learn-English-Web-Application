package pl.robert.api.app.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserDetails implements UserConstants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    String level;

    @Column(nullable = false)
    String expierience;

    @Column(name = "current_rank", length = COL_LENGTH_RANK, nullable = false)
    String currentRank;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    User user;
}
