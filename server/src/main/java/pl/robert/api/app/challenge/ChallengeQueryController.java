package pl.robert.api.app.challenge;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.challenge.domain.ChallengeFacade;

@RestController
@RequestMapping("api/challenge-query")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ChallengeQueryController {

    ChallengeFacade facade;

    @GetMapping("attacker-pending")
    public HttpEntity<?> findAttackerPendingChallenges(Authentication auth) {
        return ResponseEntity.ok(facade.queryAttackerPendingChallenges(auth.getName()));
    }

    @GetMapping("defender-pending")
    public HttpEntity<?> findDefenderPendingChallenges(Authentication auth) {
        return ResponseEntity.ok(facade.queryDefenderPendingChallenges(auth.getName()));
    }

    @GetMapping("{challengeId}")
    public HttpEntity<?> findQuestionsOfDefenderChallengeId(@PathVariable String challengeId) {
        return ResponseEntity.ok(facade.queryQuestionsOfDefenderChallengeId(challengeId));
    }
}
