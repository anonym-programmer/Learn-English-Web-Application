package pl.robert.api.app.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.user.domain.dto.CreateUserDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/base")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class BaseController {

    UserFacade facade;

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody @Valid CreateUserDTO dto, BindingResult result) {
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(facade.fillMultiMapWithErrors(result).asMap());
        }

        return ResponseEntity
                .ok()
                .body(facade.add(dto));
    }

    @GetMapping("/confirm-account")
    public HttpEntity<?> confirmAccount(@RequestParam("token") String confirmationToken) {
        boolean isCorrect = facade.confirmToken(confirmationToken);
        if (!isCorrect) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity
                .ok()
                .build();
    }
}

