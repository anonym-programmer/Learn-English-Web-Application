package pl.robert.api.app.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.shared.ErrorsWrapper;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.user.domain.dto.ChangeUserPasswordDto;
import pl.robert.api.app.user.domain.dto.CreateUserDto;
import pl.robert.api.app.user.domain.dto.ForgotUserCredentialsDto;

import javax.validation.Valid;

@RestController
@RequestMapping("api/base")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class BaseController {

    UserFacade facade;

    @PostMapping("register")
    public HttpEntity<?> register(@RequestBody @Valid CreateUserDto dto, BindingResult result) {
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorsWrapper.fillMultiMapWithErrors(result).asMap());
        }

        facade.add(dto);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("check-token")
    public HttpEntity<?> checkToken(@RequestParam("token") String token) {
        boolean isCorrect = facade.isTokenCorrect(token);
        if (!isCorrect) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("confirm-account")
    public HttpEntity<?> confirmAccount(@RequestParam("token") String confirmationToken) {
        boolean isCorrect = facade.confirmRegisterToken(confirmationToken);
        if (!isCorrect) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("forgot-credentials")
    public HttpEntity<?> receiveCredentials(@RequestBody @Valid ForgotUserCredentialsDto dto, BindingResult result) {
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorsWrapper.fillMultiMapWithErrors(result).asMap());
        }

        facade.generateResetPasswordToken(dto);
        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(value = "reset-password", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> resetPassword(@RequestParam("token") String resetPasswordToken,
                                       @RequestBody @Valid ChangeUserPasswordDto dto, BindingResult result) {
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorsWrapper.fillMultiMapWithErrors(result).asMap());
        }

        facade.changePassword(facade.findUserByConfirmationToken(resetPasswordToken), dto.getPassword());
        facade.deleteToken(resetPasswordToken);
        return ResponseEntity
                .ok()
                .build();
    }
}

