package pl.robert.api.core.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    int maxInactiveInterval;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        log.info("User {} has been logged in!", auth.getName());
        request.getSession().setMaxInactiveInterval(60 * maxInactiveInterval);
        clearAuthenticationAttributes(request);
    }
}
