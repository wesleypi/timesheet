package com.wesleypi.timesheet.controller.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecoverPasswordRequest {
    @NotBlank
    @Email
    @Schema(description = "Email para recuperacao de senha", example = "email@example.com")
    private String email;
}
