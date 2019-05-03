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

    PostService postService;
    UserFacade userFacade;

    public void add(CreatePostDto dto, String username) {
        userFacade.updateUserPosts(username);
        userFacade.addExperience(username, 40);
        postService.saveAndFlush(PostFactory.create(dto, userFacade.findUserByUsername(username)));
    }

    public Page<PostQuery> findAll(Pageable pageable) {
        return postService.findAll(pageable);
    }

    public Post findPostById(long id) {
        return postService.findById(id);
    }

    public void updatePostVote(Post post, char voteType) {
        postService.updatePostVote(post, voteType);
    }

    public void swapTypeOfVote(String newType, Post post) {
        if (newType.equalsIgnoreCase(TYPE_YES)) decreaseDownVote(post);
        else if (newType.equalsIgnoreCase(TYPE_NO)) decreaseUpVote(post);
    }

    private void decreaseDownVote(Post post) {
        postService.decreaseDownVote(post);
    }

    private void decreaseUpVote(Post post) {
        postService.decreaseUpVote(post);
    }

    public boolean isPostExists(long id) {
        return postService.isPostExists(id);
    }

    public PostQuery queryPost(long id) {
        return PostQueryFactory.queryPost(postService.findById(id));
    }
}
