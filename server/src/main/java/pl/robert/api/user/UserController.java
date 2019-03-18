package pl.robert.api.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.user.domain.UserFacade;
import pl.robert.api.user.domain.dto.CreateUserDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:4200")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class UserController {

    UserFacade facade;

    @PostMapping
    public HttpEntity<?> create(@RequestBody @Valid CreateUserDTO dto, BindingResult result) {
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .status(400)
                    .body(facade.fillMultiMapWithErrors(result).asMap());
        }

        return ResponseEntity
                .ok()
                .body(facade.add(dto));
    }
}
