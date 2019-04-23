package pl.robert.api.app.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.shared.ErrorsWrapper;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.user.domain.dto.ChangeUserEmailDto;
import pl.robert.api.app.user.domain.dto.ChangeUserPasswordDto;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserController {

    UserFacade facade;

    @PostMapping("change-password")
    public HttpEntity<?> changePassword(@RequestBody @Valid ChangeUserPasswordDto dto, BindingResult result, Authentication auth) {
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorsWrapper.fillMultiMapWithErrors(result).asMap());
        }

        facade.changePassword(facade.findUserByUsername(auth.getName()), dto.getPassword());
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("change-email")
    public HttpEntity<?> changeEmail(@RequestBody @Valid ChangeUserEmailDto dto, BindingResult result, Authentication auth) {
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorsWrapper.fillMultiMapWithErrors(result).asMap());
        }

        facade.changeEmail(facade.findUserByUsername(auth.getName()), dto.getEmail());
        return ResponseEntity
                .ok()
                .build();
    }
}
