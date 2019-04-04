package pl.robert.api.app.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public HttpEntity<?> getUserProfile(Authentication auth) {
        return ResponseEntity
                .ok()
                .body(facade.queryUserProfile(auth.getName()));
    }
}
