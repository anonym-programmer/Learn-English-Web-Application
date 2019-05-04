package pl.robert.api.app.challenge;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.robert.api.app.challenge.domain.ChallengeFacade;
import pl.robert.api.app.challenge.query.*;
import pl.robert.api.app.question.query.QuestionQuery;

import java.util.List;

@RestController
@RequestMapping("api/challenge-query")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ChallengeQueryController {

    ChallengeFacade facade;

    @GetMapping("attacker-pending")
    public HttpEntity<List<SubmitedChallengeQuery>> queryAttackerPendingChallenges(Authentication auth) {
        return ResponseEntity.ok(facade.queryAttackerPendingChallenges(auth.getName()));
    }

    @GetMapping("defender-pending")
    public HttpEntity<List<PendingChallengeQuery>> queryDefenderPendingChallenges(Authentication auth) {
        return ResponseEntity.ok(facade.queryDefenderPendingChallenges(auth.getName()));
    }

    @GetMapping("defender-pending/{challengeId}")
    public HttpEntity<List<QuestionQuery>> queryQuestionsOfDefenderChallengeId(@PathVariable String challengeId) {
        return ResponseEntity.ok(facade.queryQuestionsOfDefenderChallengeId(challengeId));
    }

    @GetMapping("completed")
    public HttpEntity<List<CompletedChallengeQuery>> queryCompletedChallenges(Authentication auth) {
        return ResponseEntity.ok(facade.queryCompletedChallenges(auth.getName()));
    }

    @GetMapping("completed/{challengeId}")
    public HttpEntity<CompletedChallengeDetailsQuery> queryCompletedChallengeDetailsByChallengeId(@PathVariable String challengeId, Authentication auth) {
        return ResponseEntity.ok(facade.queryCompletedChallengeDetailsByChallengeId(challengeId, auth.getName()));
    }

    @GetMapping("completed/correct-answers/{challengeId}")
    public HttpEntity<AnsweredChallengeQuery> queryCompletedChallengeDetailsCorrectAnswersByChallengeId(@PathVariable String challengeId, Authentication auth) {
        return ResponseEntity.ok(facade.queryCompletedChallengeDetailsCorrectAnswersByChallengeId(challengeId, auth.getName()));
    }
}
