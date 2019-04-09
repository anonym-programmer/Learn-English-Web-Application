package pl.robert.api.app.post.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
import pl.robert.api.app.post.query.PostQuery;

import java.util.Comparator;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class PostService {

    PostRepository repository;

    void saveAndFlush(Post post) {
        repository.saveAndFlush(post);
    }

    Page<PostQuery> findAll(Pageable pageable) {
        Page<Post> postsPage = repository.findAll(pageable);
        int totalElements = (int) postsPage.getTotalElements();
        return new PageImpl<>(postsPage
                .stream()
                .map(post -> new PostQuery(
                        String.valueOf(post.getId()),
                        post.getTitle(),
                        post.getDescription().length() > 70 ? post.getDescription().substring(0, 67).concat("...") : post.getDescription(),
                        String.valueOf(post.getDate()),
                        String.valueOf(post.getUpVote()),
                        String.valueOf(post.getDownVote()),
                        post.getUser().getUsername()))
                .sorted(Comparator.comparing(PostQuery::getId))
                .collect(Collectors.toList()), pageable, totalElements);
    }

    Post findById(long id) {
        return repository.findById(id);
    }

    void updatePostVote(Post post, char voteType) {
        if (Character.toUpperCase(voteType) == 'Y') {
            int currentUpVote = post.getUpVote();
            post.setUpVote(++currentUpVote);
        } else {
            int currentDownVote = post.getDownVote();
            post.setDownVote(++currentDownVote);
        }
        repository.saveAndFlush(post);
    }

    void decreaseDownVote(Post post) {
        int currentDownVote = post.getDownVote();
        post.setDownVote(--currentDownVote);
        repository.saveAndFlush(post);
    }

    void decreaseUpVote(Post post) {
        int currentUpVote = post.getUpVote();
        post.setUpVote(--currentUpVote);
        repository.saveAndFlush(post);
    }

    boolean isPostExists(long id) {
        return repository.findById(id) != null;
    }
}
