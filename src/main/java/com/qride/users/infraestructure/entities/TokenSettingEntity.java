package com.qride.users.infraestructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import com.qride.users.infraestructure.entities.enums.TokenTypeEntity;

@Getter
@Setter
@Entity
@Table(name = "token_settings")
public class TokenSettingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_setting_id", nullable = false)
    private Long id;

    @ColumnDefault("1")
    @Column(name = "is_active")
    private Boolean isActive;

    @NotNull
    @Column(name = "token_expiration_days", nullable = false)
    private Integer tokenExpiration;

    @NotNull
    @Column(name = "refresh_token_expiration_days", nullable = false)
    private Integer refreshTokenExpiration;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "token_type", nullable = false)
    private TokenTypeEntity tokenTypeEntity;

}