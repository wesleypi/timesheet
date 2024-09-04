package com.wesleypi.timesheet.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HolidayResponse {
    @Schema(description = "Holiday's year", example = "2018")
    private Integer year;

    @Schema(description = "Holiday's days", example = "[ \"06-24\" , \"09-30\" ]")
    private List<String> days;
}
