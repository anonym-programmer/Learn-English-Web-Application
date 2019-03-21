package pl.robert.api.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static pl.robert.api.user.domain.UserConstants.COL_LENGTH_ROLE;

@Entity
@Table(name = "roles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = COL_LENGTH_ROLE, nullable = false)
    String role;
}
