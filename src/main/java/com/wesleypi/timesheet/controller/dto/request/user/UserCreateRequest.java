package com.wesleypi.timesheet.controller.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    @NotBlank
    @Schema(description = "User name.", example = "John Doe")
    private String name;

    @NotBlank
    @Schema(description = "User email.", example = "email@example.com")
    @Email
    private String email;

    @NotBlank
    @Schema(description = "User password.", example = "42208806077")
    private String password;

    @NotBlank
    @Schema(description = "User team.", example = "Bhut team")
    private String team;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "User hour value.", example = "17.05")
    private BigDecimal hourValue;

    @NotNull
    @Schema(description = "Define if user has bank hours.", example = "true")
    private Boolean hasBankHours;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "User contract monthly value.", example = "2176.50")
    private BigDecimal contractTotal;

    @NotBlank
    @Schema(description = "User group identifier with 8 digits.", example = "33121245")
    private String groupId;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "User start date.", example = "2022-01-04")
    private LocalDate startDate;
}
