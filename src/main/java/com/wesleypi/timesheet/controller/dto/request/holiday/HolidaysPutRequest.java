package com.wesleypi.timesheet.controller.dto.request.holiday;

import com.wesleypi.timesheet.controller.dto.request.DateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HolidaysPutRequest {
    @Schema(description = "Holiday's days with format: month-day", example = "[ \"06-24\" , \"09-30\" ]")
    private List<DateRequest> days;
}
