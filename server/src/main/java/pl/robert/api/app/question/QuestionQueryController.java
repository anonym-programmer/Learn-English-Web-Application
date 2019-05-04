package pl.robert.api.app.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.robert.api.app.question.domain.QuestionFacade;
import pl.robert.api.app.question.query.QuestionQuery;

import java.util.List;

@RestController
@RequestMapping("api/question-query")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class QuestionQueryController {

    QuestionFacade facade;

    @GetMapping("random-questions")
    public HttpEntity<List<QuestionQuery>> queryRandomQuestions() {
        return ResponseEntity.ok(facade.queryRandomQuestions());
    }
}
