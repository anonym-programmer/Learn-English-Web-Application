package pl.robert.api.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static pl.robert.api.user.domain.UserValidator.COL_LENGTH_MAX_LOGIN;
import static pl.robert.api.user.domain.UserValidator.COL_LENGTH_MAX_PASSWORD;

@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = COL_LENGTH_MAX_LOGIN, unique = true, nullable = false)
    String login;

    @Column(unique = true, nullable = false)
    String email;

    @Column(length = COL_LENGTH_MAX_PASSWORD, nullable = false)
    String password;

    @Column(name = "is_enabled")
    boolean isEnabled;
}
