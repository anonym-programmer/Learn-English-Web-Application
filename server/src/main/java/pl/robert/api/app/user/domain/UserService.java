package pl.robert.api.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pl.robert.api.app.user.domain.dto.SignInDto;
import pl.robert.api.app.user.query.UserQuery;

import java.util.*;
import java.util.stream.Collectors;

import static pl.robert.api.app.shared.Constants.*;

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

    boolean isntUserAnAdmin(long id) {
        return repository.findById(id).getRoles().size() == 1;
    }

    boolean isUserByIdExist(Long id) {
        return repository.findById(id).isPresent();
    }

    boolean isUsernameNotExist(String username) {
        return repository.findByUsername(username) == null;
    }

    boolean isEmailExist(String email) {
        return repository.findByEmail(email) != null;
    }

    User findById(long id) {
        return repository.findById(id);
    }

    User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    Page<UserQuery> findAll(Pageable pageable) {
        return new PageImpl<>(repository.findAll(pageable)
                .stream()
                .map(user -> new UserQuery(
                        String.valueOf(user.getId()),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles().size() == 1 ? USER : USER_ADMIN,
                        user.isEnabled()))
                .sorted(Comparator.comparing(UserQuery::getId))
                .collect(Collectors.toList()), pageable, repository.findAll(pageable).getTotalElements());
    }

    Optional<SignInDto> querySignInByUsername(String username) {
        return Optional.ofNullable(repository.findByUsername(username)).stream()
                .map(user -> new SignInDto(
                        username,
                        user.getPassword(),
                        user.isEnabled(),
                        user.getRoles()))
                .findFirst();
    }

    String queryRandomUsername(String attackerUsername) {

        if (repository.count() == 1) return attackerUsername;

        String defenderUsername;

        do {
            defenderUsername = repository.findRandomUsername();
        } while (attackerUsername.equals(defenderUsername));
        return defenderUsername;
    }
}
