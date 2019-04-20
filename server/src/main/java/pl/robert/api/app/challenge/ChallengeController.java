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
import pl.robert.api.app.question.domain.QuestionFacade;
import pl.robert.api.app.shared.ErrorsWrapper;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/challenge")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChallengeController {

    ChallengeFacade challengeFacade;
    QuestionFacade questionFacade;

    @PostMapping("/make")
    public HttpEntity<?> makeChallenge(@RequestBody @Valid MakeChallengeDto dto, BindingResult result, Authentication auth) {
        dto.setAttackerUsername(auth.getName());
        challengeFacade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorsWrapper.fillMultiMapWithErrors(result).asMap());
        }

        return ResponseEntity
                .ok()
                .body(questionFacade.getRandomQuestions());
    }

    @PostMapping("/submit")
    public HttpEntity<?> submitChallenge(@RequestBody @Valid SubmitChallengeDto dto, BindingResult result, Authentication auth) {
        dto.setAttackerUsername(auth.getName());
        challengeFacade.checkInputData(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorsWrapper.fillMultiMapWithErrors(result).asMap());
        }

        challengeFacade.add(dto);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> declineChallenge(@PathVariable String id, Authentication auth) {
        if (!challengeFacade.isInputDataCorrect(new DeleteChallengeDto(Long.parseLong(id), auth.getName()))) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        challengeFacade.delete(Long.parseLong(id));
        return ResponseEntity
                .ok()
                .build();
    }
}
