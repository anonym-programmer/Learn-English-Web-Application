package pl.robert.api.app.post.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.robert.api.app.post.domain.dto.CreatePostDto;
import pl.robert.api.app.post.query.PostQuery;
import pl.robert.api.app.user.domain.UserFacade;

import static pl.robert.api.app.shared.Constants.TYPE_NO;
import static pl.robert.api.app.shared.Constants.TYPE_YES;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostFacade {

    PostService service;
    UserFacade userFacade;

    public void add(CreatePostDto dto, String username) {
        userFacade.updateUserPosts(username);
        userFacade.addExperience(username, 40);
        service.saveAndFlush(PostFactory.create(dto, userFacade.findUserByUsername(username)));
    }

    public Page<PostQuery> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    public Post findPostById(long id) {
        return service.findById(id);
    }

    public void updatePostVote(Post post, char voteType) {
        service.updatePostVote(post, voteType);
    }

    public void swapTypeOfVote(String newType, Post post) {
        service.swapTypeOfVote(newType, post);
    }

    public boolean isPostExists(long id) {
        return service.isPostExists(id);
    }

    public PostQuery queryPost(long id) {
        return PostQueryFactory.queryPost(service.findById(id));
    }
}
