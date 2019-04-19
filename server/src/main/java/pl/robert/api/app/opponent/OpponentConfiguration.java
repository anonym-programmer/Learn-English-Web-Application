package pl.robert.api.app.opponent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpponentConfiguration {

    @Bean
    OpponentFacade opponentFacade(OpponentRepository repository) {

        return new OpponentFacade(new OpponentService(repository));
    }
}
