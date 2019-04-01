package pl.robert.api.core.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.user.domain.UserQueryFactory;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class AuthController {

    UserFacade facade;

    @GetMapping
    public ResponseEntity getAuth(Authentication auth) {
        if (auth != null) {
            return ResponseEntity
                    .ok()
                    .body(UserQueryFactory
                            .query(facade.findUserByUsername(auth.getName())));
        }
        return ResponseEntity
                .badRequest()
                .build();
    }
}
