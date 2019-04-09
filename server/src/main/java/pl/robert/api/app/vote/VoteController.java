package pl.robert.api.app.vote;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.shared.ErrorsWrapper;
import pl.robert.api.app.vote.domain.VoteFacade;
import pl.robert.api.app.vote.domain.dto.CreateVoteDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/vote")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class VoteController {

    VoteFacade facade;

    @PostMapping
    public HttpEntity<?> addVote(@RequestBody @Valid CreateVoteDto dto, BindingResult result) {
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
