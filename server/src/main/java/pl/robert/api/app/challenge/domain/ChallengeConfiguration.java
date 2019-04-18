package pl.robert.api.app.challenge.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.api.app.user.domain.UserFacade;

@Configuration
class ChallengeConfiguration {

    @Bean
    ChallengeFacade challengeFacade(ChallengeRepository repository,
                                    UserFacade userFacade,
                                    QuestionRepository questionRepository) {

        QuestionService questionService = new QuestionService(questionRepository);

        return new ChallengeFacade(new ChallengeValidator(userFacade, questionService),
                                   new ChallengeService(repository),
                                   questionService);
    }
}
