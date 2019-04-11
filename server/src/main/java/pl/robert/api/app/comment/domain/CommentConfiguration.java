package pl.robert.api.app.comment.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.api.app.post.domain.PostFacade;
import pl.robert.api.app.user.domain.UserFacade;

@Configuration
class CommentConfiguration {

    @Bean
    CommentFacade commentFacade(CommentRepository commentRepository,
                                PostFacade postFacade,
                                UserFacade userFacade) {
        return new CommentFacade(new CommentValidator(postFacade, userFacade),
                                 new CommentService(commentRepository),
                                 postFacade,
                                 userFacade);
    }
}
