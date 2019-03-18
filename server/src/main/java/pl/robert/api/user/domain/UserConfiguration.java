package pl.robert.api.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade facade(UserRepository repository) {
        return new UserFacade(repository,
                              new UserValidator(repository));
    }
}
