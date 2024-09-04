package com.wesleypi.timesheet.usecase.holiday;

import com.wesleypi.timesheet.controller.dto.request.holiday.HolidaysPutRequest;
import com.wesleypi.timesheet.exception.ResourceConflictException;
import com.wesleypi.timesheet.persistence.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.wesleypi.timesheet.helper.mapper.HolidayMapper.HOLIDAY_MAPPER;

@Service
@RequiredArgsConstructor
public class PutHolidaysUseCase {

    final HolidayRepository holidayRepository;

    public void execute(final Integer year, final HolidaysPutRequest holidayPutRequest) {
        final var findHolidaysEntities = holidayRepository.findAllByYear(year);

        if (Objects.isNull(findHolidaysEntities) || findHolidaysEntities.isEmpty()) {
            throw new ResourceConflictException("Não existe feriados para o ano: " + year);
        }

        holidayRepository.deleteAllByYear(year);

        holidayRepository.saveAll(HOLIDAY_MAPPER.requestToEntities(year, holidayPutRequest.getDays()));
    }
}
