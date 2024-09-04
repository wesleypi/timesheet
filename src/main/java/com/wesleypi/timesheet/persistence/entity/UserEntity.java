package com.wesleypi.timesheet.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", indexes = {@Index(name = "idx_id_externo_user", columnList = "id_externo")})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_externo", unique = true, nullable = false, updatable = false)
    @Builder.Default
    private String externalId = UUID.randomUUID().toString();

    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_grupo", nullable = false)
    private GroupEntity group;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "time")
    private String team;

    @Setter
    @Column(name = "senha", nullable = false)
    private String password;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate startDate;

    @Column(name = "valor_hora", nullable = false)
    private BigDecimal hourValue;

    @Column(name = "valor_mensal", nullable = false)
    private BigDecimal contractTotal;

    @Builder.Default
    @Column(name = "banco_horas", nullable = false)
    private Long hoursBank = 0L;
}
