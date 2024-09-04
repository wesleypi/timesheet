package com.wesleypi.timesheet.controller.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    @NotBlank
    @Schema(description = "Codigo de recuperacao", example = "123456")
    @Pattern(regexp = "\\d{6}", message = "o codigo precisa ter 6 digitos")
    private String code;

    @NotBlank
    @Schema(description = "Nova senha", example = "admin123")
    private String newPassword;
}
