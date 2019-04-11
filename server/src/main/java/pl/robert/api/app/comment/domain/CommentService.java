package pl.robert.api.app.comment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CommentService {

    CommentRepository repository;

    void saveAndFlush(Comment comment) {
        repository.saveAndFlush(comment);
    }
}
