package pl.robert.api.app.admin;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("http://localhost:4200")
class AdminController {

    @GetMapping
    public HttpEntity<?> dashboard() {
        return ResponseEntity.status(200).body("Welcome admin!");
    }
}
