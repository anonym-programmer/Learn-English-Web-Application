package pl.robert.api.app.challenge.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ChallengeConfiguration {

    @Bean
    ChallengeFacade challengeFacade(ChallengeRepository repository) {
        return new ChallengeFacade(new ChallengeValidator(),
                                   new ChallengeService(repository));
    }
}
