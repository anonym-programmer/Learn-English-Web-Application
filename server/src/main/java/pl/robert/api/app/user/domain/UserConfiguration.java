package pl.robert.api.app.user.domain;

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
        TokenService tokenService = new TokenService(tokenRepository, userService, mailSender);

        return new UserFacade(new UserValidator(userService, tokenService),
                              userService,
                              tokenService);
    }
}
