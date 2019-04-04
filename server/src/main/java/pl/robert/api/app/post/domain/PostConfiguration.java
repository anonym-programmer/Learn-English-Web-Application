package pl.robert.api.app.post.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.api.app.user.domain.UserFacade;

@Configuration
class PostConfiguration {

    @Bean
    PostFacade postFacade(PostRepository postRepository, UserFacade userFacade) {

        PostService postService = new PostService(postRepository);

        return new PostFacade(postService,
                              userFacade);
    }
}
