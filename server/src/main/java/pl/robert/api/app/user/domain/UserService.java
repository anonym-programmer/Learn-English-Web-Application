package pl.robert.api.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pl.robert.api.app.user.domain.dto.AuthUserDto;
import pl.robert.api.app.user.query.UserQuery;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.robert.api.app.shared.Constants.ROLE_USER;
import static pl.robert.api.app.shared.Constants.ROLE_USER_ADMIN;

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

    boolean isUserByIdExist(long id) {
        return repository.findById(id) != null;
    }

    boolean isUsernameExist(String username) {
        return repository.findByUsername(username) != null;
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

    Page<UserQuery> findAll(Pageable pageable) {
        Page<User> usersPage = repository.findAll(pageable);
        int totalElements = (int) usersPage.getTotalElements();
        return new PageImpl<>(usersPage
                .stream()
                .map(user -> new UserQuery(
                        String.valueOf(user.getId()),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles().size() == 1 ? ROLE_USER : ROLE_USER_ADMIN,
                        user.isEnabled()))
                .sorted(Comparator.comparing(UserQuery::getId))
                .collect(Collectors.toList()), pageable, totalElements);
    }
}
