package com.wesleypi.timesheet.controller.impl;

import com.wesleypi.timesheet.controller.TimesheetController;
import com.wesleypi.timesheet.controller.dto.request.EntryPutRequest;
import com.wesleypi.timesheet.controller.dto.response.MonthlyReportPaginatedResponse;
import com.wesleypi.timesheet.controller.dto.response.Pagination;
import com.wesleypi.timesheet.usecase.GetMonthlyEntriesReportUseCase;
import com.wesleypi.timesheet.usecase.timesheet.PutEntryUseCase;
import com.wesleypi.timesheet.validation.RequestValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.time.Month;

@RestController
@RequiredArgsConstructor
@Validated
public class TimesheetControllerImpl implements TimesheetController {

    private final RequestValidator validator;
    private final GetMonthlyEntriesReportUseCase getMonthlyEntriesReportUseCase;
    private final PutEntryUseCase putEntryUseCase;

    @Override
    public MonthlyReportPaginatedResponse getMonthDays(final Integer year, final Integer month, final Integer page, final Integer pageSize) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final var id = attr.getRequest().getHeader("user_id");
        return getMonthlyEntriesReportUseCase.execute(year, Month.of(month), id, new Pagination(page,pageSize));
    }

    @Override
    public ResponseEntity<Object> putDay(final Integer year, final Integer month, final Integer day, final EntryPutRequest entryPutRequest) {
        validator.validate(entryPutRequest);
        final var dayRequest = LocalDate.of(year, month, day);
        putEntryUseCase.execute(dayRequest, entryPutRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
