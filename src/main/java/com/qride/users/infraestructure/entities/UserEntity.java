package com.qride.users.infraestructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.qride.users.infraestructure.entities.enums.UserStatusEntity;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @NotNull
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "user_uuid", nullable = false, length = 36)
    private UUID userUuid;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "activated_at")
    private LocalDate activatedAt;

    @Column(name = "deleted_at")
    private LocalDate deletedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatusEntity status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private ContactEntity contactEntity;

}