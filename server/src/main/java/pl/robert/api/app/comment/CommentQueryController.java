package pl.robert.api.app.comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.comment.domain.CommentFacade;

@RestController
@RequestMapping("api/comment-query")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CommentQueryController {

    CommentFacade facade;

    @GetMapping("{id}")
    public HttpEntity<?> findAllByPost(@PathVariable String id) {
        if (!facade.isPostExists(Long.parseLong(id))) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity.ok(facade.queryCommentsByPost(Long.parseLong(id)));
    }
}
