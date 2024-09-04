package com.wesleypi.timesheet.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grupos", indexes = {@Index(name = "idx_id_externo_grupos", columnList = "id_externo")})
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_externo", unique = true, nullable = false, updatable = false)
    @Builder.Default
    private String externalId = String.format("%08d", new SecureRandom().nextInt(100_000_000));

    @Column(name = "nome", nullable = false, unique = true, updatable = false)
    private String name;
}