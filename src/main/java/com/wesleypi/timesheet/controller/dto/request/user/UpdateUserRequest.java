package com.wesleypi.timesheet.controller.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotBlank
    @Schema(description = "User name.", example = "John Doe")
    private String name;

    @Schema(description = "User email.", example = "email@domain.com")
    @Email(message = "Must declare a valid e-mail")
    private String email;

    @NotBlank
    @Schema(description = "User team.", example = "Bhut team")
    private String team;

    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "User hour value.", example = "17.05")
    private BigDecimal hourValue;

    @Schema(description = "Define if user has bank hours.", example = "true")
    private Boolean hasBankHours;

    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "User contract total hours.", example = "176")
    private Integer contractTotal;

    @NotBlank
    @Schema(description = "User group identifier.", example = "21541254")
    private String groupId;

    @Schema(description = "User start date.", example = "2022-01-04")
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
}
