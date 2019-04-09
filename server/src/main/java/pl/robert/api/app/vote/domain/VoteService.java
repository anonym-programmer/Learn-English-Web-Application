package pl.robert.api.app.vote.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class VoteService {

    VoteRepository repository;

    void saveAndFlush(Vote vote) {
        repository.saveAndFlush(vote);
    }

    boolean isTypeCorrect(String type) {
        return type.equalsIgnoreCase("YES") || type.equalsIgnoreCase("NO");
    }
}
