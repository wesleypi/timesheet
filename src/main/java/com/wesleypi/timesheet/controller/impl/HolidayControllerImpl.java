package com.wesleypi.timesheet.controller.impl;

import com.wesleypi.timesheet.controller.HolidayController;
import com.wesleypi.timesheet.controller.dto.request.holiday.HolidayCreateRequest;
import com.wesleypi.timesheet.controller.dto.request.holiday.HolidaysPutRequest;
import com.wesleypi.timesheet.controller.dto.response.HolidaysPaginatedResponse;
import com.wesleypi.timesheet.usecase.holiday.CreateHolidaysUseCase;
import com.wesleypi.timesheet.usecase.holiday.GetHolidaysUseCase;
import com.wesleypi.timesheet.usecase.holiday.PutHolidaysUseCase;
import com.wesleypi.timesheet.validation.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HolidayControllerImpl implements HolidayController {

    private final RequestValidator validator;
    private final CreateHolidaysUseCase createHolidaysUseCase;
    private final GetHolidaysUseCase getHolidaysUseCase;
    private final PutHolidaysUseCase putHolidaysUseCase;

    @Override
    public HolidaysPaginatedResponse getHolidays(final Integer page, final Integer pageSize) {
        return getHolidaysUseCase.execute(page, pageSize);
    }

    @Override
    public ResponseEntity<Object> postHolidays(final HolidayCreateRequest holidayCreateRequest) {
        validator.validate(holidayCreateRequest);
        createHolidaysUseCase.execute(holidayCreateRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Object> updateHoliday(final int year, final HolidaysPutRequest holidaysPutRequest) {
        validator.validate(holidaysPutRequest);
        putHolidaysUseCase.execute(year, holidaysPutRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
