package pl.robert.api.app.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static pl.robert.api.app.shared.Constants.COL_LENGTH_CONFIRMATION_TOKEN;
import static pl.robert.api.app.shared.Constants.COL_LENGTH_DATE_IN_SECONDS;

@Entity
@Table(name = "tokens")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "token", length = COL_LENGTH_CONFIRMATION_TOKEN)
    String confirmationToken;

    @Column(name = "created_date_in_seconds", length = COL_LENGTH_DATE_IN_SECONDS)
    String createdDateInSeconds;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    Token(User user) {
        this.user = user;
        createdDateInSeconds = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        confirmationToken = UUID.randomUUID().toString();
    }
}
