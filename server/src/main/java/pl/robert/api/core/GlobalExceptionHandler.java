package pl.robert.api.core;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE)
class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    Map<String, String> exception = new HashMap<>();

    @ExceptionHandler(Exception.class)
    public HttpEntity<?> handleException(HttpServletRequest request, final Exception e) {
        String title;
        String message;

        if (e instanceof MailSendException) {
            title = "MessagingException || MailSendException";
            message = "Mail exception apeared...";
        } else {
            title = "Exception";
            message = "Exception apeared...";
        }
        e.printStackTrace();
        logger.warn("{} on {} URL", title, request.getRequestURL());

        exception.clear();
        exception.put("title", title);
        exception.put("message", message);

        return ResponseEntity
                .badRequest()
                .body(exception);
    }
}
