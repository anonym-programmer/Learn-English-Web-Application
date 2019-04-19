package pl.robert.api.app.opponent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.api.app.question.domain.QuestionFacade;
import pl.robert.api.app.user.domain.UserFacade;

@Configuration
class OpponentConfiguration {

    @Bean
    OpponentFacade opponentFacade(OpponentRepository repository,
                                  QuestionFacade questionFacade,
                                  UserFacade userFacade) {

        return new OpponentFacade(new OpponentService(repository, questionFacade, userFacade));
    }
}
