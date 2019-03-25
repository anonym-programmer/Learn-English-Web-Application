package pl.robert.api.app.user.domain;

import com.google.common.collect.Multimap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.user.domain.dto.ChangePasswordDTO;
import pl.robert.api.app.user.domain.dto.CreateUserDTO;
import pl.robert.api.app.user.domain.dto.ForgotCredentialsDTO;
import pl.robert.api.app.user.query.CreateUserQuery;
import pl.robert.api.core.security.dto.AuthorizationDTO;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {

    UserValidator validator;
    UserService userService;
    TokenService tokenService;

    public void checkInputData(CreateUserDTO dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void checkInputData(ForgotCredentialsDTO dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public void checkInputData(ChangePasswordDTO dto, BindingResult result) {
        if (!result.hasErrors()) {
            validator.checkInputData(dto, result);
        }
    }

    public boolean isTokenCorrect(String token) {
        tokenService.cleanAllExpiredTokens();
        return tokenService.findByConfirmationToken(token) != null;
    }

    public Multimap<String, String> fillMultiMapWithErrors(BindingResult result) {
        return userService.fillMultiMapWithErrors(result);
    }

    public CreateUserQuery add(CreateUserDTO dto) {
        User user = UserFactory.create(dto);
        userService.saveAndFlush(user);
        tokenService.generateRegisterToken(user);

        return UserQuery.query(dto);
    }

    public boolean confirmRegisterToken(String confirmationToken) {
        return tokenService.confirmRegisterToken(confirmationToken);
    }

    public void generateResetPasswordToken(ForgotCredentialsDTO dto) {
        tokenService.generateResetPasswordToken(userService.findByEmail(dto.getEmail()));
    }

    public void changePassword(ChangePasswordDTO dto, String resetPasswordToken) {
        User user = tokenService.findByConfirmationToken(resetPasswordToken).getUser();
        user.setPassword(passwordEncoder().encode(dto.getPassword()));
        userService.saveAndFlush(user);
        tokenService.deleteToken(resetPasswordToken);
    }

    public AuthorizationDTO findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Bean
    private static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
