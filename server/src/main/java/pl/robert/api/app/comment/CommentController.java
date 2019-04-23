package pl.robert.api.app.comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.comment.domain.CommentFacade;
import pl.robert.api.app.comment.domain.dto.CreateCommentDto;
import pl.robert.api.app.shared.ErrorsWrapper;

import javax.validation.Valid;

@RestController
@RequestMapping("api/comment")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CommentController {

    CommentFacade facade;

    @PostMapping
    public HttpEntity<?> addComment(@RequestBody @Valid CreateCommentDto dto, BindingResult result, Authentication auth) {
        dto.setUsername(auth.getName());
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorsWrapper.fillMultiMapWithErrors(result).asMap());
        }

        facade.add(dto);
        return ResponseEntity
                .ok()
                .build();
    }
}

