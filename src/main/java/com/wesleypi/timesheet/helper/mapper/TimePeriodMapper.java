package com.wesleypi.timesheet.helper.mapper;

import com.wesleypi.timesheet.controller.dto.request.EntryPeriodRequest;
import com.wesleypi.timesheet.controller.dto.response.PeriodResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import com.wesleypi.timesheet.model.TimePeriod;

import java.util.Comparator;
import java.util.List;

@Mapper
public interface TimePeriodMapper {
    TimePeriodMapper TIME_PERIOD_MAPPER = Mappers.getMapper(TimePeriodMapper.class);

    @Mapping(target = "start", source = "start")
    List<TimePeriod> toModels(List<EntryPeriodRequest> timePeriods);

    TimePeriod toModel(EntryPeriodRequest entryPeriodRequest);

    @AfterMapping
    default void sortTimePeriods(@MappingTarget List<TimePeriod> timePeriods) {
        timePeriods.sort(Comparator.comparing(TimePeriod::getStart));
    }

    List<PeriodResponse> toResponses(List<TimePeriod> timePeriod);

    PeriodResponse toResponse(TimePeriod timePeriod);
}
