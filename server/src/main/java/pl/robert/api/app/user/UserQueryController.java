package pl.robert.api.app.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.user.query.RandomUserQuery;
import pl.robert.api.app.user.query.UserOwnProfileQuery;

@RestController
@RequestMapping("api/user-query")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserQueryController {

    UserFacade facade;

    @GetMapping("my-profile")
    public HttpEntity<UserOwnProfileQuery> queryUserOwnProfile(Authentication auth) {
        return ResponseEntity.ok(facade.queryUserOwnProfile(auth.getName()));
    }

    @GetMapping("challenge-profile/{username}")
    public HttpEntity<?> queryUserChallengeProfile(@PathVariable String username) {
        if (facade.isUsernameNotExists(username)) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity.ok(facade.queryUserChallengeProfile(username));
    }

    @GetMapping("forum-profile/{username}")
    public HttpEntity<?> queryUserForumProfile(@PathVariable String username) {
        if (facade.isUsernameNotExists(username)) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity.ok(facade.queryUserForumProfile(username));
    }

    @GetMapping("profile/{username}")
    public HttpEntity<?> queryUserProfile(@PathVariable String username) {
        if (facade.isUsernameNotExists(username)) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity.ok(facade.queryUserProfile(username));
    }

    @GetMapping("random-user")
    public HttpEntity<RandomUserQuery> queryRandomUser(Authentication auth) {
        return ResponseEntity.ok(facade.queryRandomUser(auth.getName()));
    }
}
