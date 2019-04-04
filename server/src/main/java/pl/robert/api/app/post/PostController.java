package pl.robert.api.app.post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.post.domain.PostFacade;
import pl.robert.api.app.post.domain.dto.CreatePostDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class PostController {

    PostFacade facade;

    @PostMapping
    public HttpEntity<?> add(@RequestBody @Valid CreatePostDto dto, BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(facade.fillMultiMapWithErrors(result).asMap());
        }

        facade.add(dto, auth.getName());
        return ResponseEntity
                .ok()
                .build();
    }
}
