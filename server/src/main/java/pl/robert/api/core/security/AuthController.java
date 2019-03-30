package pl.robert.api.core.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200")
class AuthController {

    @GetMapping
    public ResponseEntity getAuth(Authentication auth) {
        if (auth != null) {
            return ResponseEntity
                    .ok()
                    .body(auth);
        }
        return ResponseEntity
                .badRequest()
                .build();
    }
}
