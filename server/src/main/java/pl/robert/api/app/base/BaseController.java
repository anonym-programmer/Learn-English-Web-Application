package pl.robert.api.app.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.user.domain.dto.ChangePasswordDTO;
import pl.robert.api.app.user.domain.dto.CreateUserDTO;
import pl.robert.api.app.user.domain.dto.ForgotCredentialsDTO;

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

    @PostMapping("/forgot-credentials")
    public HttpEntity<?> receiveCredentials(@RequestBody @Valid ForgotCredentialsDTO dto, BindingResult result) {
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(facade.fillMultiMapWithErrors(result).asMap());
        }
        facade.generateResetPasswordToken(dto);

        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(value = "/reset-password", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> resetPassword(@RequestParam("token") String resetPasswordToken,
                                       @RequestBody @Valid ChangePasswordDTO dto, BindingResult result) {
        boolean isCorrect = facade.confirmResetPasswordToken(resetPasswordToken);
        if (!isCorrect) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(facade.fillMultiMapWithErrors(result).asMap());
        }
        facade.changePassword(dto, resetPasswordToken);

        return ResponseEntity
                .ok()
                .build();
    }
}

