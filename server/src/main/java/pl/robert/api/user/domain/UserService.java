package pl.robert.api.user.domain;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import pl.robert.api.security.dto.AuthorizationDTO;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserService {

    UserRepository repository;

    Multimap<String, String> fillMultiMapWithErrors(BindingResult result) {
        Multimap<String, String> errors = ArrayListMultimap.create();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    void saveAndFlush(User user) {
        repository.saveAndFlush(user);
    }

    void delete(User user) {
        repository.delete(user);
    }

    User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    AuthorizationDTO findByUsername(String username) {
        User user = repository.findByUsername(username);

        if (user == null) return null;

        return new AuthorizationDTO(user.getUsername(), user.getPassword(), user.isEnabled(), user.getRoles());
    }
}
