package pl.robert.api.core.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.robert.api.app.user.domain.UserFacade;
import pl.robert.api.core.security.dto.AuthUserDTO;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserDetailsServiceImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserDTO user = userFacade.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        if (!user.isEnabled()) {
            user.setPassword(UUID.randomUUID().toString());
            logger.warn("User is not verified! Changing password to random UUID!");
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
