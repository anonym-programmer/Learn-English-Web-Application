package pl.robert.api.core.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.eraseCredentials(false);
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    private static final String[] SWAGGER_API = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(SWAGGER_API).permitAll()
                .antMatchers("/api/base/*",
                                         "/api/auth",
                                         "/api/post-query/**").permitAll()
                .antMatchers("/api/user*").hasRole("USER")
                .antMatchers("/api/admin*").hasRole("ADMIN")
                .antMatchers("/api/post*",
                                         "/api/vote*").authenticated()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .successForwardUrl("/api/user")
                .successHandler(new CustomAuthenticationSuccessHandler())
                .failureHandler(new CustomAuthenticationFailureHandler())
                .permitAll()
            .and()
                .logout()
                .logoutUrl("/j_spring_security_logout")
                .logoutSuccessHandler(new LogoutSuccessHandler(HttpStatus.OK))
                .deleteCookies("JSESSIONID", "XSRF-TOKEN")
                .invalidateHttpSession(true)
                .permitAll()
            .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers("/j_spring_security_check", "/j_spring_security_logout", "/api/**")
            .and()
                .cors()
            .and()
                .headers().frameOptions().sameOrigin()
            .and()
                .sessionManagement().maximumSessions(1);
    }
}
