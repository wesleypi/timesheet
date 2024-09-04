package com.wesleypi.timesheet.controller.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPasswordRequest {
    @NotBlank
    @Schema(description = "User password.", example = "42208806077")
    private String password;
}
