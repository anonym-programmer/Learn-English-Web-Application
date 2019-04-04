package pl.robert.api.app.user.domain;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import pl.robert.api.app.user.domain.dto.AuthUserDto;

import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserService {

    UserRepository repository;

    void saveAndFlush(User user) {
        repository.saveAndFlush(user);
    }

    void delete(User user) {
        repository.delete(user);
    }

    boolean isUsernameExist(String username) {
        return repository.findByUsername(username) != null;
    }

    boolean isEmailExist(String email) {
        return repository.findByEmail(email) != null;
    }

    Multimap<String, String> fillMultiMapWithErrors(BindingResult result) {
        Multimap<String, String> errors = ArrayListMultimap.create();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    Optional<AuthUserDto> findAuthByUsername(String username) {
        User user = repository.findByUsername(username);
        return Optional.of(
                AuthUserDto
                        .builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .isEnabled(user.isEnabled())
                        .roles(user.getRoles())
                        .build());
    }
}
