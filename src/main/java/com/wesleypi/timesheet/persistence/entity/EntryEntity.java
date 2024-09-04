package com.wesleypi.timesheet.persistence.entity;

import com.wesleypi.timesheet.model.TimePeriod;
import com.wesleypi.timesheet.persistence.converter.TimePeriodConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lancamentos", indexes = {@Index(name = "date_idx", columnList = "data")})
public class EntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity user;

    @Column(name = "data", nullable = false)
    private LocalDate date;

    @Column(name = "feriado", nullable = false)
    private Boolean isHoliday;

    @Column(name = "periodos", nullable = false, columnDefinition = "json")
    @Convert(converter = TimePeriodConverter.class)
    private List<TimePeriod> periods;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime startTime;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime endTime;

    @Column(name = "descricao")
    private String description;

    @Column(nullable = false)
    private Long total;
}
