package com.wesleypi.timesheet.helper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntryMapper {
    EntryMapper ENTRY_MAPPER = Mappers.getMapper(EntryMapper.class);

    //EntryResponse toModel(EntryEntity entity);
}
