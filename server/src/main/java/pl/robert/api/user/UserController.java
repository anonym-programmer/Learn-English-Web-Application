package pl.robert.api.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.robert.api.user.domain.UserFacade;
import pl.robert.api.user.domain.dto.CreateUserDTO;
import pl.robert.api.user.query.CreateUserQuery;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
class UserController {

    private UserFacade facade;

    @PostMapping
    public HttpEntity<?> create(@RequestBody @Valid CreateUserDTO dto) {
        CreateUserQuery user = facade.add(dto);

        return ResponseEntity.ok(user);
    }
}
