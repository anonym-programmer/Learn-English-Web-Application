package pl.robert.api.app.vote.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.api.app.post.domain.PostFacade;
import pl.robert.api.app.user.domain.UserFacade;

@Configuration
class VoteConfiguration {

    @Bean
    VoteFacade voteFacade(VoteRepository voteRepository,
                          PostFacade postFacade,
                          UserFacade userFacade) {

        VoteService voteService = new VoteService(voteRepository);

        return new VoteFacade(new VoteValidator(voteService, postFacade, userFacade),
                              voteService,
                              postFacade,
                              userFacade);
    }
}
