package pl.robert.api.app.post.domain;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import pl.robert.api.app.post.query.PostQuery;

import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class PostService {

    PostRepository repository;

    Multimap<String, String> fillMultiMapWithErrors(BindingResult result) {
        Multimap<String, String> errors = ArrayListMultimap.create();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

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

    boolean isPostExists(long id) {
        return repository.findById(id) != null;
    }
}
