package com.wesleypi.timesheet.controller.dto.request.holiday;

import com.wesleypi.timesheet.controller.dto.request.DateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HolidayCreateRequest {
    @NotNull
    @Schema(description = "Holiday's year", example = "2018")
    private Integer year;

    @Schema(description = "Holiday's days", example = "[ \"2024-01-01\" , \"2024-12-25\" ]")
    private List<@NotNull DateRequest> days;
}
