package com.wesleypi.timesheet.usecase.timesheet;

import com.wesleypi.timesheet.controller.dto.request.EntryPutRequest;
import com.wesleypi.timesheet.exception.ResourceConflictException;
import com.wesleypi.timesheet.exception.ResourceNotFoundException;
import com.wesleypi.timesheet.model.TimePeriod;
import com.wesleypi.timesheet.persistence.repository.EntryRepository;
import com.wesleypi.timesheet.persistence.repository.HolidayRepository;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static com.wesleypi.timesheet.helper.DateUtil.isClosedMonth;
import static com.wesleypi.timesheet.helper.DateUtil.isWeekend;
import static com.wesleypi.timesheet.helper.TimeIntervalUtil.calculateTimePeriods;
import static com.wesleypi.timesheet.helper.TimeIntervalUtil.holidayPeriods;
import static com.wesleypi.timesheet.helper.mapper.TimePeriodMapper.TIME_PERIOD_MAPPER;
import static com.wesleypi.timesheet.helper.mapper.custom.EntryMapperCustom.updateEntity;

@Service
@RequiredArgsConstructor
public class PutEntryUseCase {

    private final EntryRepository entryRepository;
    private final HolidayRepository holidayRepository;
    private final UserRepository userRepository;

    public void execute(final LocalDate dayRequest, final EntryPutRequest entryPutRequest) {
        if (isWeekend(dayRequest)){
            throw new ResourceConflictException("Não é possivel fazer lançamentos no fim de semana");
        }

        // TODO: final var user = HttpHeaders.headers.firstValue("user_id").orElse(null);
        final var foundUser = userRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (isClosedMonth(dayRequest.getMonth(), dayRequest.getYear())) {
            throw new ResourceConflictException("O Mês já está fechado");
        }

        final var isHoliday = holidayRepository.existsByDate(dayRequest);

        final var periodsRequest =  isHoliday ? holidayPeriods()    : TIME_PERIOD_MAPPER.toModels(entryPutRequest.getPeriods());
        final var durationRequest = isHoliday ? Duration.ofHours(8) : calculateTimePeriods(periodsRequest);
        final var description =     isHoliday ? "Feriado"           : entryPutRequest.getDescription();

        final var foundEntry = entryRepository.findByDate(dayRequest).orElse(null);

        final var entry = updateEntity(dayRequest, periodsRequest, durationRequest.toMillis(), description, isHoliday, foundEntry, foundUser);

        entryRepository.save(entry);
    }

}
