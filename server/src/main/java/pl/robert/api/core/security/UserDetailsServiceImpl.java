package pl.robert.api.core.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.app.user.domain.dto.SignInDto;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserDetailsServiceImpl implements UserDetailsService {

    UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SignInDto user = userFacade.querySignInByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        if (!user.isEnabled()) {
            user.setPassword(UUID.randomUUID().toString());
            log.warn("User is not verified! Changing password to random UUID!");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getRole()))
                        .collect(Collectors.toSet()));
    }
}
