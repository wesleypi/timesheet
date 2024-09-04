package com.wesleypi.timesheet.controller.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserHoursBankRequest {
    @NotNull
    @Min(value = 0L)
    @Schema(description = "Tempo a ser diminuido", example = "720000")
    private Long balance;

    @NotBlank
    @Schema(description = "Descricao do lancamento", example = "Pagamento banco horas")
    private String description;

    @Schema(description = "Data banco de horas", example = "2022-01-04")
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
}
