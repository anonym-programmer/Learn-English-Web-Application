package pl.robert.api.app.post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.post.domain.PostFacade;
import pl.robert.api.app.post.query.PostQuery;

@RestController
@RequestMapping("api/post-query")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class PostQueryController {

    PostFacade facade;

    @GetMapping
    public Page<PostQuery> findAll(Pageable pageable) {
        return facade.findAll(pageable);
    }

    @GetMapping("{id}")
    public HttpEntity<?> getPost(@PathVariable String id) {
        if (!facade.isPostExists(Long.parseLong(id))) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity.ok(facade.queryPost(Long.parseLong(id)));
    }
}
