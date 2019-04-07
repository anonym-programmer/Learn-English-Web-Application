package pl.robert.api.app.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.user.domain.UserFacade;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserQueryController {

    UserFacade facade;

    @GetMapping
    public HttpEntity<?> dashboard() {
        return ResponseEntity.status(200).body("Welcome user!");
    }

    @GetMapping("/my-profile")
    public HttpEntity<?> getUserOwnProfile(Authentication auth) {
        return ResponseEntity
                .ok()
                .body(facade.queryUserOwnProfile(auth.getName()));
    }

    @GetMapping("/profile/{username}")
    public HttpEntity<?> getUserProfile(@PathVariable String username) {
        if (!facade.isUserExists(username)) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity
                .ok()
                .body(facade.queryUserProfile(username));
    }
}
