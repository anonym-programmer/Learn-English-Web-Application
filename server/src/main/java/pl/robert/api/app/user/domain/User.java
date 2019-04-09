package pl.robert.api.app.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static pl.robert.api.app.shared.Constants.COL_LENGTH_ENCODED_PASSWORD;
import static pl.robert.api.app.shared.Constants.COL_LENGTH_MAX_USERNAME;

@Entity
@Table(name = "users")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = COL_LENGTH_MAX_USERNAME, unique = true, nullable = false)
    String username;

    @Column(unique = true, nullable = false)
    String email;

    @Column(length = COL_LENGTH_ENCODED_PASSWORD, nullable = false)
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles = new HashSet<>();

    @Column(name = "is_enabled")
    boolean isEnabled;
}
