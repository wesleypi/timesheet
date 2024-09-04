package com.wesleypi.timesheet.persistence.entity;

import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagamentos")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity user;

    @Column(name = "mes", nullable = false)
    private Integer month;

    @Column(name = "ano", nullable = false)
    private Integer year;

    @Column(name = "total_horas", nullable = false)
    private Long totalHours;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate date;

    @Column(name = "valor_hora", nullable = false)
    private BigDecimal hourValue;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "banco_horas_atual", nullable = false)
    private Long currentHoursBank;
}
