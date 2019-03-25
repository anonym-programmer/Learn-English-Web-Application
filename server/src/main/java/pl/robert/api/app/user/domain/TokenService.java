package pl.robert.api.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class TokenService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    TokenRepository tokenRepository;
    UserService userService;
    JavaMailSender mailSender;

    void generateRegisterToken(User user) {
        Token registerToken = new Token(user);
        tokenRepository.saveAndFlush(registerToken);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            String htmlMsg = "To confirm your account, please follow the link below:<br>" +
                    "<a href='http://localhost:4200/confirm-account?token=" + registerToken.getConfirmationToken() + "'>" +
                    "http://localhost:4200/confirm-account?token=" + registerToken.getConfirmationToken() + "</a>";
            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject("Complete Registration!");
            helper.setFrom("Rob");
            mailSender.send(mimeMessage);
        } catch (MessagingException | MailException e) {
            tokenRepository.delete(registerToken);
            userService.delete(user);
            logger.info("Deleting token and user cause {} appeared...", e.getClass());
            throw new MailSendException("MessagingException | MailException");
        }
    }

    boolean confirmRegisterToken(String confirmationToken) {
        cleanAllExpiredTokens();
        Token token = tokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userService.findByEmail(token.getUser().getEmail());

            user.setEnabled(true);
            userService.saveAndFlush(user);

            tokenRepository.delete(token);
            logger.info("Account confirmation correct!");
            return true;
        }
        logger.warn("Token has been expired.");
        return false;
    }

    void generateResetPasswordToken(User user) {
        Token resetPasswordToken = new Token(user);
        tokenRepository.save(resetPasswordToken);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            String htmlMsg = "Thank you for your password reset request. Your login is: <b>" + user.getUsername() + "</b><br>" +
                    "Please follow the link below to reset your password:<br>" +
                    "<a href='http://localhost:4200/reset-password?token=" + resetPasswordToken.getConfirmationToken() + "'>" +
                    "http://localhost:4200/reset-password?token=" + resetPasswordToken.getConfirmationToken() + "</a>";
            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject("Forgotten Password!");
            helper.setFrom("Rob");
            mailSender.send(mimeMessage);
        } catch (MessagingException | MailSendException e) {
            tokenRepository.delete(resetPasswordToken);
            logger.info("Deleted token cause {} appeared...", e.getClass());
            throw new MailSendException("MailSendException | MessagingException");
        }
    }

    void cleanAllExpiredTokens() {
        List<Token> tokens = tokenRepository.findAll();
        for (Token token : tokens) {
            Token tokenToCheck = tokenRepository.findById(token.getId());
            if (getCurrentTimeInSeconds() - Long.parseLong(tokenToCheck.getCreatedDateInSeconds()) > 900) {
                tokenRepository.delete(tokenToCheck);
            }
        }
    }

    private long getCurrentTimeInSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis()));
    }

    Token findByConfirmationToken(String confirmationToken) {
        return tokenRepository.findByConfirmationToken(confirmationToken);
    }

    void deleteToken(String confirmationToken) {
        tokenRepository.delete(findByConfirmationToken(confirmationToken));
    }

    Token findByUser(User user) {
        return tokenRepository.findByUser(user);
    }
}
