package pl.robert.api.app.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository userRepository,
                          UserDetailsRepository detailsRepository,
                          TokenRepository tokenRepository,
                          JavaMailSender mailSender) {

        UserService userService = new UserService(userRepository);
        UserDetailsService detailsService = new UserDetailsService(detailsRepository);
        TokenService tokenService = new TokenService(tokenRepository, userService, detailsService, mailSender);

        return new UserFacade(new UserValidator(userService, tokenService),
                              userService,
                              detailsService,
                              tokenService);
    }
}
