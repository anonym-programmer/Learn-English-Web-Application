package pl.robert.api.core;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
class GlobalExceptionHandler {

    @ExceptionHandler({MailSendException.class, MailAuthenticationException.class})
    public HttpEntity<?> handleException(HttpServletRequest request, final Exception e) {
        e.printStackTrace();
        log.warn("{} on {} URL", HttpStatus.UNPROCESSABLE_ENTITY, request.getRequestURL());

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .build();
    }
}
