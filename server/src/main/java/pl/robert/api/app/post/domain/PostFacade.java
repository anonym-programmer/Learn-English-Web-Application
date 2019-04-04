package pl.robert.api.app.post.domain;

import com.google.common.collect.Multimap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import pl.robert.api.app.post.domain.dto.CreatePostDto;
import pl.robert.api.app.user.domain.UserFacade;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostFacade {

    PostService postService;
    UserFacade userFacade;

    public Multimap<String, String> fillMultiMapWithErrors(BindingResult result) {
        return postService.fillMultiMapWithErrors(result);
    }

    public void add(CreatePostDto dto, String username) {
        Post post = PostFactory.create(dto, userFacade.findUserByUsername(username));
        postService.saveAndFlush(post);
    }
}
