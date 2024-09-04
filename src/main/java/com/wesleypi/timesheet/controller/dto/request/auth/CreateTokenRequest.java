package com.wesleypi.timesheet.controller.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTokenRequest {
    @NotBlank
    @Email
    @Schema(description = "Email do usuario", example = "email@domain.com")
    private String email;

    @NotBlank
    @Schema(description = "Senha do usuario", example = "42208806077")
    private String password;
}
