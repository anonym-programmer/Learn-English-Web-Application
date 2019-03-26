package pl.robert.api.core;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
class GlobalExceptionHandler {

    Map<String, String> exception = new HashMap<>();

    @ExceptionHandler(Exception.class)
    public HttpEntity<?> handleException(HttpServletRequest request, final Exception e) {
        String title;
        String message;

        if (e instanceof MailSendException || e instanceof MailAuthenticationException) {
            title = "MessagingException || MailAuthenticationException";
            message = "Mail exception appeared...";
        } else {
            title = "Exception";
            message = "Exception appeared...";
        }
        e.printStackTrace();
        log.warn("{} on {} URL", title, request.getRequestURL());

        exception.clear();
        exception.put("title", title);
        exception.put("message", message);

        return ResponseEntity
                .unprocessableEntity()
                .body(exception);
    }
}
