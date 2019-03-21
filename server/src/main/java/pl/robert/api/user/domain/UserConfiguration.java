package pl.robert.api.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade facade(UserRepository userRepository,
                      TokenRepository tokenRepository,
                      JavaMailSender mailSender) {

        UserService userService = new UserService(userRepository);

        return new UserFacade(new UserValidator(userRepository),
                              userService,
                              new TokenService(tokenRepository, userService, mailSender));
    }
}
