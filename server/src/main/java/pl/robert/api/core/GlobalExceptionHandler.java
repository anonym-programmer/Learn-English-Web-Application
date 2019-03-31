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

    @ExceptionHandler(Exception.class)
    public HttpEntity<?> handleException(HttpServletRequest request, final Exception e) {
        HttpStatus httpStatus;

        if (e instanceof MailSendException || e instanceof MailAuthenticationException) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        } else {
            httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        }
        e.printStackTrace();
        log.warn("{} on {} URL", httpStatus, request.getRequestURL());

        return ResponseEntity
                .status(httpStatus)
                .build();
    }
}
