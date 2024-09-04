package com.wesleypi.timesheet.usecase.user;

import com.wesleypi.timesheet.controller.dto.request.EntryPutRequest;
import com.wesleypi.timesheet.controller.dto.request.user.UpdateUserRequest;
import com.wesleypi.timesheet.exception.ResourceConflictException;
import com.wesleypi.timesheet.exception.ResourceNotFoundException;
import com.wesleypi.timesheet.persistence.repository.EntryRepository;
import com.wesleypi.timesheet.persistence.repository.HolidayRepository;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

import static com.wesleypi.timesheet.helper.DateUtil.isClosedMonth;
import static com.wesleypi.timesheet.helper.DateUtil.isWeekend;
import static com.wesleypi.timesheet.helper.TimeIntervalUtil.calculateTimePeriods;
import static com.wesleypi.timesheet.helper.TimeIntervalUtil.holidayPeriods;
import static com.wesleypi.timesheet.helper.mapper.TimePeriodMapper.TIME_PERIOD_MAPPER;
import static com.wesleypi.timesheet.helper.mapper.UserMapper.USER_MAPPER;
import static com.wesleypi.timesheet.helper.mapper.custom.EntryMapperCustom.updateEntity;

@Service
@RequiredArgsConstructor
public class PutUserByIdUseCase {

    private final UserRepository userRepository;

    public void execute(final String id, final UpdateUserRequest updateUserRequest) {
        final var foundUser = userRepository.findByExternalId(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        final var userRequest = USER_MAPPER.toEntity(updateUserRequest);

        userRepository.save(USER_MAPPER.updateEntity(foundUser, userRequest));
    }

}
