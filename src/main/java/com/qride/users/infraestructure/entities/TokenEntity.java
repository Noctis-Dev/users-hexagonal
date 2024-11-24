package com.qride.users.infraestructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import com.qride.users.infraestructure.entities.enums.TokenTypeEntity;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tokens")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "token", nullable = false)
    private String token;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "token_type", nullable = false)
    private TokenTypeEntity tokenTypeEntity;

    @NotNull
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "token_setting_id")
    private TokenSettingEntity tokenSettingEntity;

}