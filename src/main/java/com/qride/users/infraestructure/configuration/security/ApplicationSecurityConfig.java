package com.qride.users.infraestructure.configuration.security;

import com.qride.users.infraestructure.configuration.security.filters.JwtAuthorizationFilter;
import com.qride.users.infraestructure.configuration.security.filters.RateLimitFilter;
import com.qride.users.infraestructure.configuration.security.filters.UserAuthenticationFilter;
import com.qride.users.infraestructure.configuration.security.jwt.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

    @Autowired
    private AuthenticationManager userAuthManager;

    @Autowired
    private JwtAuthorizationFilter authorizationFilter;

    @Autowired
    private RateLimitFilter rateLimitFilter;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private SecretKey secretKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        UserAuthenticationFilter userAuthenticationFilter = new UserAuthenticationFilter(jwtConfig, secretKey);
        userAuthenticationFilter.setAuthenticationManager(userAuthManager);
        userAuthenticationFilter.setFilterProcessesUrl("/user/login");

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        /* FILTERS */
        http.addFilter(userAuthenticationFilter)
//                .addFilterBefore(rateLimitFilter, UserAuthenticationFilter.class)
                .addFilterBefore(authorizationFilter, UserAuthenticationFilter.class);

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/**").permitAll();
        });

        return http.httpBasic(Customizer.withDefaults()).build();
    }

}
