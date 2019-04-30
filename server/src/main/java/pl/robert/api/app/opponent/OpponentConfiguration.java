package pl.robert.api.app.opponent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.api.app.user.domain.UserFacade;

@Configuration
class OpponentConfiguration {

    @Bean
    OpponentFacade opponentFacade(OpponentRepository repository,
                                  UserFacade userFacade) {
        return new OpponentFacade(new OpponentService(repository, userFacade));
    }
}
