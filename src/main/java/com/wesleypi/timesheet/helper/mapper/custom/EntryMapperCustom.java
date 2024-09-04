package com.wesleypi.timesheet.helper.mapper.custom;

import com.wesleypi.timesheet.model.TimePeriod;
import com.wesleypi.timesheet.persistence.entity.EntryEntity;
import com.wesleypi.timesheet.persistence.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntryMapperCustom {
    public static EntryEntity updateEntity(LocalDate date, List<TimePeriod> periods, Long total, String description, Boolean isHoliday, EntryEntity entryEntity, UserEntity userEntity) {

        EntryEntity.EntryEntityBuilder entry = EntryEntity.builder();

        if (entryEntity != null) {
            if (entryEntity.getId() != null) {
                entry.id(entryEntity.getId());
            }
        }
        if (userEntity != null) {
            entry.user(userEntity);
        }


        return entry
                .date(date)
                .description(description)
                .isHoliday(isHoliday)
                .startTime(periods.getFirst().getStart())
                .endTime(periods.getLast().getEnd())
                .periods(periods)
                .total(total)
                .build();
    }
}
