package com.wesleypi.timesheet.helper.mapper;

import com.wesleypi.timesheet.controller.dto.request.DateRequest;
import com.wesleypi.timesheet.controller.dto.response.HolidayResponse;
import com.wesleypi.timesheet.controller.dto.response.HolidaysPaginatedResponse;
import com.wesleypi.timesheet.controller.dto.response.Pagination;
import com.wesleypi.timesheet.persistence.entity.HolidayEntity;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Mapper
public interface HolidayMapper {
    HolidayMapper HOLIDAY_MAPPER = Mappers.getMapper(HolidayMapper.class);

    default List<HolidayEntity> requestToEntities(final Integer year, final List<DateRequest> dateRequests) {
        return dateRequests
                .stream()
                .map(dateRequest -> HolidayEntity.builder()
                        .year(year)
                        .date(dateRequest.getDate())
                        .build())
                .toList();
    }

    HolidayResponse toResponse(HolidayEntity holidayEntity);

    default HolidaysPaginatedResponse toHolidaysPaginatedResponse(final Page<HolidayEntity> holidays) {
        var HolidaysPaginatedResponse = new HolidaysPaginatedResponse();

        if (isNull(holidays)) {
            return HolidaysPaginatedResponse;
        }

        final var pageable = holidays.getPageable();
        HolidaysPaginatedResponse.setPagination(new Pagination(pageable.getPageNumber(), pageable.getPageSize(), holidays.getTotalElements()));

        if (!holidays.hasContent()) {
            return HolidaysPaginatedResponse;
        }

        final var holidayMapYear = toHolidayMap(holidays.getContent());

        final var holidaysResponse = holidayMapYear.keySet().stream()
                .map((key) -> new HolidayResponse(key, datesToString(holidayMapYear.get(key)))).toList();

        HolidaysPaginatedResponse.setHolidays(holidaysResponse);
        return HolidaysPaginatedResponse;
    }

    private static Map<Integer, List<LocalDate>> toHolidayMap(List<HolidayEntity> holidays) {
        return holidays.stream()
                .collect(Collectors.groupingBy(
                        HolidayEntity::getYear,
                        Collectors.mapping(HolidayEntity::getDate, Collectors.toList())
                ));
    }

    private static List<String> datesToString(List<LocalDate> dates) {
        //final var MONTH_DAY = DateTimeFormatter.ofPattern("MM-dd");

        return dates.stream().map(LocalDate::toString).toList();
    }
}

