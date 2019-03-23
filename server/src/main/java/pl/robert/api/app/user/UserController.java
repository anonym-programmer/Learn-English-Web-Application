package pl.robert.api.app.user;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:4200")
class UserController {

    @GetMapping
    public HttpEntity<?> dashboard() {
        return ResponseEntity.status(200).body("Welcome user!");
    }
}
