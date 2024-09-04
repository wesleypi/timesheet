package com.wesleypi.timesheet.controller;

import com.wesleypi.timesheet.controller.dto.request.EntryPutRequest;
import com.wesleypi.timesheet.controller.dto.response.MonthlyReportPaginatedResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "timesheet", description = "Resources for timesheet management")
@RequestMapping("/timesheet")
@Validated
public interface TimesheetController {

    @GetMapping("/{year}/{month}")
    MonthlyReportPaginatedResponse getMonthDays(@PathVariable Integer year, @PathVariable Integer month, @RequestParam(defaultValue = "0") final Integer page, @RequestParam(defaultValue = "20") final Integer pageSize);

    @PutMapping("/{year}/{month}/{day}")
    ResponseEntity<Object> putDay(@PathVariable Integer year, @PathVariable final Integer month, @PathVariable final Integer day, @RequestBody final EntryPutRequest entry);
}
