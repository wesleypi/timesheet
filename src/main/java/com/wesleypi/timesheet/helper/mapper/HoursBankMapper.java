package com.wesleypi.timesheet.helper.mapper;

import com.wesleypi.timesheet.controller.dto.request.user.UpdateUserHoursBankRequest;
import com.wesleypi.timesheet.persistence.entity.HoursBankEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HoursBankMapper {
    HoursBankMapper HOURS_BANK_MAPPER = Mappers.getMapper(HoursBankMapper.class);

    HoursBankEntity toEntity(final UpdateUserHoursBankRequest request, final Long hours);

}

