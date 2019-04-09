package pl.robert.api.app.vote.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.post.domain.Post;
import pl.robert.api.app.user.domain.User;

import static pl.robert.api.app.shared.Constants.TYPE_NO;
import static pl.robert.api.app.shared.Constants.TYPE_YES;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class VoteService {

    VoteRepository repository;

    void saveAndFlush(Vote vote) {
        repository.saveAndFlush(vote);
    }

    void delete(Vote vote) {
        repository.delete(vote);
    }

    Vote findByPostAndUser(Post post, User user) {
        return repository.findByPostAndUser(post, user);
    }

    boolean isTypeCorrect(String type) {
        return type.equalsIgnoreCase(TYPE_YES) || type.equalsIgnoreCase(TYPE_NO);
    }

    boolean hasUserAlreadyVoted(Post post, User user) {
        return repository.findByPostAndUser(post, user) != null;
    }

    boolean isOldTypeMatchNewType(Post post, User user, String type) {
        return repository.findByPostAndUser(post, user).getType() == type.toUpperCase().charAt(0);
    }
}
