package com.qride.users.infraestructure.configuration.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ConfigurationProperties(prefix = "token")
public class TokenSettings {

    private Long jwtTokenExpirationDays;

}
