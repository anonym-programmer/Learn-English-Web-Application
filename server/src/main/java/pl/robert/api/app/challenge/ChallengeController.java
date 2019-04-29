package pl.robert.api.app.challenge;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.challenge.domain.ChallengeFacade;
import pl.robert.api.app.challenge.domain.dto.MakeChallengeDto;
import pl.robert.api.app.challenge.domain.dto.DeleteChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitChallengeDto;
import pl.robert.api.app.challenge.domain.dto.SubmitPendingChallengeDto;
import pl.robert.api.app.shared.ErrorsWrapper;

import javax.validation.Valid;

@RestController
@RequestMapping("api/challenge")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ChallengeController {

    ChallengeFacade facade;

    @PostMapping("make")
    public HttpEntity<?> makeChallenge(@RequestBody @Valid MakeChallengeDto dto, BindingResult result, Authentication auth) {
        dto.setAttackerUsername(auth.getName());
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorsWrapper.fillMultiMapWithErrors(result).asMap());
        }

        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("submit")
    public HttpEntity<?> submitChallenge(@RequestBody @Valid SubmitChallengeDto dto, BindingResult result, Authentication auth) {
        dto.setAttackerUsername(auth.getName());
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorsWrapper.fillMultiMapWithErrors(result).asMap());
        }

        facade.submitChallenge(dto);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> declineChallenge(@PathVariable String id, Authentication auth) {
        if (!facade.isInputDataCorrect(new DeleteChallengeDto(Long.parseLong(id), auth.getName()))) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        facade.delete(Long.parseLong(id));
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("submit-pending")
    public HttpEntity<?> submitPendingChallenge(@RequestBody SubmitPendingChallengeDto dto, Authentication auth) {
        dto.setDefenderUsername(auth.getName());
        facade.submitPendingChallenge(dto);

        return ResponseEntity
                .ok()
                .build();
    }
}
