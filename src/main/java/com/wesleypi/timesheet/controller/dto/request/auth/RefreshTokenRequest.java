package com.wesleypi.timesheet.controller.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {
    @NotBlank
    @Schema(description = "Token a ser renovado", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjODk4Zjg2ZS00NTQ3LTQ5NjQtOTZhNS0yYTJlYTUzNTk0YzkiLCJpYXQiOjE3MjU0NDI4Mjl9.MBaRaDEQRlT-rmsGiC2olfu2p96Vcrwk--6M0zAxBks")
    private String refreshToken;
}
