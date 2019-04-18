package pl.robert.api.app.challenge.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.api.app.question.domain.QuestionFacade;
import pl.robert.api.app.user.domain.UserFacade;

@Configuration
class ChallengeConfiguration {

    @Bean
    ChallengeFacade challengeFacade(UserFacade userFacade,
                                    QuestionFacade questionFacade) {

        return new ChallengeFacade(new ChallengeValidator(userFacade, questionFacade));
    }
}
