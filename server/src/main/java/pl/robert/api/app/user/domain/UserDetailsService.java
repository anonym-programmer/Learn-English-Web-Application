package pl.robert.api.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserDetailsService {

    UserDetailsRepository repository;

    void saveAndFlush(UserDetails details) {
        repository.saveAndFlush(details);
    }

    UserDetails findUserDetailsById(long id) {
        return repository.findUserDetailsById(id);
    }
}
