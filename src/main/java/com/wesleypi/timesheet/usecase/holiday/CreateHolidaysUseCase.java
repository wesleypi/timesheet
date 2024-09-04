package com.wesleypi.timesheet.usecase.holiday;

import com.wesleypi.timesheet.controller.dto.request.holiday.HolidayCreateRequest;
import com.wesleypi.timesheet.exception.ResourceConflictException;
import com.wesleypi.timesheet.persistence.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.wesleypi.timesheet.helper.mapper.HolidayMapper.HOLIDAY_MAPPER;

@Service
@RequiredArgsConstructor
public class CreateHolidaysUseCase {

    final HolidayRepository holidayRepository;

    public void execute(final HolidayCreateRequest holidayCreateRequest) {
        final var holiday = holidayRepository.findFirstByYear(holidayCreateRequest.getYear());

        if (holiday.isPresent()){
            throw new ResourceConflictException("Já existem feriados no ano: " + holidayCreateRequest.getYear());
        }

        final var holidaysEntities = HOLIDAY_MAPPER.requestToEntities(holidayCreateRequest.getYear(), holidayCreateRequest.getDays());

        holidayRepository.saveAll(holidaysEntities);
    }
}
