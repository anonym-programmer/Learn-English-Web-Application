package pl.robert.api.user.domain;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import pl.robert.api.user.domain.dto.CreateUserDTO;
import pl.robert.api.user.query.CreateUserQuery;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class UserFacade {

    UserRepository repository;
    UserValidator validator;

    public CreateUserQuery add(CreateUserDTO dto) {
        repository.saveAndFlush(UserFactory.create(dto));
        return UserQuery.query(dto);
    }

    public void checkInputData(CreateUserDTO dto, BindingResult result) {
        validator.checkInputData(dto, result);
    }

    public Multimap<String, String> fillMultiMapWithErrors(BindingResult result) {
        Multimap<String, String> errors = ArrayListMultimap.create();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }
}
