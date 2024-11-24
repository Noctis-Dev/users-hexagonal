package com.qride.users.infraestructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "credentials")
public class CredentialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credential_id", nullable = false)
    private Long id;

    @NotNull
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "credential_uuid", nullable = false, length = 36)
    private UUID credentialUuid;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "verified_at")
    private LocalDate verifiedAt;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private ContactEntity contactEntity;

    @OneToOne
    @JoinColumn(name = "token_id")
    private TokenEntity tokenEntity;
}