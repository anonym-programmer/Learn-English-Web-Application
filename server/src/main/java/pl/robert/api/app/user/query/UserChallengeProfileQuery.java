package pl.robert.api.app.user.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChallengeProfileQuery {
    
    String username;
    String level;
    String numberOfWins;
    String numberOfLoses;
    String numberOfDraws;
}
