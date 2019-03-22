package pl.robert.api.user.domain;

import com.google.common.collect.Multimap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.security.dto.AuthorizationDTO;
import pl.robert.api.user.domain.dto.CreateUserDTO;
import pl.robert.api.user.query.CreateUserQuery;

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

    public Multimap<String, String> fillMultiMapWithErrors(BindingResult result) {
        return userService.fillMultiMapWithErrors(result);
    }

    public CreateUserQuery add(CreateUserDTO dto) {
        User user = UserFactory.create(dto);
        userService.saveAndFlush(user);
        tokenService.generateToken(user);

        return UserQuery.query(dto);
    }

    public boolean confirmToken(String confirmationToken) {
        return tokenService.confirmToken(confirmationToken);
    }

    public AuthorizationDTO findByLogin(String login) {
        return userService.findByLogin(login);
    }
}
