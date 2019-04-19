package pl.robert.api.app.challenge.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.api.app.opponent.OpponentFacade;
import pl.robert.api.app.question.domain.QuestionFacade;
import pl.robert.api.app.user.domain.UserFacade;

@Configuration
class ChallengeConfiguration {

    @Bean
    ChallengeFacade challengeFacade(ChallengeRepository repository,
                                    UserFacade userFacade,
                                    QuestionFacade questionFacade,
                                    OpponentFacade opponentFacade) {

        ChallengeService service = new ChallengeService(repository, opponentFacade, questionFacade, userFacade);

        return new ChallengeFacade(new ChallengeValidator(service, userFacade, questionFacade),
                                   service);
    }
}
