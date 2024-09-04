package com.wesleypi.timesheet.usecase.holiday;

import com.wesleypi.timesheet.controller.dto.response.HolidaysPaginatedResponse;
import com.wesleypi.timesheet.persistence.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.wesleypi.timesheet.helper.mapper.HolidayMapper.HOLIDAY_MAPPER;

@Service
@RequiredArgsConstructor
public class GetHolidaysUseCase {

    final HolidayRepository holidayRepository;

    public HolidaysPaginatedResponse execute(final Integer page, final Integer pageSize) {
        final var pagedHolidays = holidayRepository.findAll(PageRequest.of(page, pageSize, Sort.by("year").descending()));

        return HOLIDAY_MAPPER.toHolidaysPaginatedResponse(pagedHolidays);
    }
}
