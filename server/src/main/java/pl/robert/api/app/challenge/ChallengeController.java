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
import pl.robert.api.app.challenge.domain.dto.ChooseChallengeOponentDto;
import pl.robert.api.app.challenge.domain.dto.CreateChallengeDto;
import pl.robert.api.app.shared.ErrorsWrapper;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/challenge")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChallengeController {

    ChallengeFacade facade;

    @PostMapping("/choose")
    public HttpEntity<?> chooseChallengeOpponent(@RequestBody @Valid ChooseChallengeOponentDto dto, BindingResult result, Authentication auth) {
        dto.setAttackerUsername(auth.getName());
        facade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorsWrapper.fillMultiMapWithErrors(result).asMap());
        }

        return ResponseEntity
                .ok()
                .body(facade.getRandomQuestions());
    }

    @PostMapping("/make")
    public HttpEntity<?> makeChallenge(@RequestBody @Valid CreateChallengeDto dto, BindingResult result, Authentication auth) {
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
}
