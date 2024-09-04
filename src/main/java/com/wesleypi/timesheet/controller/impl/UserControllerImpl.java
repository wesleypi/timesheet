package com.wesleypi.timesheet.controller.impl;

import com.wesleypi.timesheet.controller.UserController;
import com.wesleypi.timesheet.controller.dto.request.user.UpdateUserHoursBankRequest;
import com.wesleypi.timesheet.controller.dto.request.user.UpdateUserPasswordRequest;
import com.wesleypi.timesheet.controller.dto.request.user.UpdateUserRequest;
import com.wesleypi.timesheet.controller.dto.request.user.UserCreateRequest;
import com.wesleypi.timesheet.controller.dto.response.MonthlyReportPaginatedResponse;
import com.wesleypi.timesheet.controller.dto.response.Pagination;
import com.wesleypi.timesheet.controller.dto.response.PatchUserMonthlyReportResponse;
import com.wesleypi.timesheet.controller.dto.response.UserPaginatedResponse;
import com.wesleypi.timesheet.controller.dto.response.UserResponse;
import com.wesleypi.timesheet.controller.dto.response.auth.UserCreateResponse;
import com.wesleypi.timesheet.usecase.GetMonthlyEntriesReportUseCase;
import com.wesleypi.timesheet.usecase.user.*;
import com.wesleypi.timesheet.validation.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final RequestValidator validator;
    private final CreateUserUseCase createUserUseCase;
    private final CloseMonthlyReportUseCase closeMonthlyReportUseCase;
    private final GetMonthlyEntriesReportUseCase getMonthlyEntriesReportUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final PutUserByIdUseCase putUserByIdUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final UpdateUserHoursBankUseCase updateUserHoursBankUseCase;

    @Override
    public UserCreateResponse createUser(final UserCreateRequest createRequest) {
        validator.validate(createRequest);
        return createUserUseCase.execute(createRequest);
    }

    @Override
    public UserPaginatedResponse getAllUsers(Integer page, Integer pageSize) {
        return getAllUsersUseCase.execute(page, pageSize);
    }

    @Override
    public UserResponse getUserById(String id) {
        validator.validate(id);
        return getUserByIdUseCase.execute(id);
    }

    @Override
    public ResponseEntity<Object> putUserPassword(final String id, final UpdateUserPasswordRequest updateUserPasswordRequest) {
        validator.validate(id);
        validator.validate(updateUserPasswordRequest);
        updatePasswordUseCase.execute(id, updateUserPasswordRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Object> putUserById(final String id, final UpdateUserRequest updateUserRequest) {
        validator.validate(id);
        validator.validate(updateUserRequest);
        putUserByIdUseCase.execute(id, updateUserRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public PatchUserMonthlyReportResponse patchReport(final String id, final Integer year, final Integer month) {
        validator.validate(id);
        return closeMonthlyReportUseCase.execute(id, year, month);
    }

    @Override
    public ResponseEntity<Object> patchUserHoursBank(final String id, final UpdateUserHoursBankRequest updateUserHoursBank) {
        validator.validate(id);
        validator.validate(updateUserHoursBank);
        updateUserHoursBankUseCase.execute(id, updateUserHoursBank);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public MonthlyReportPaginatedResponse getMonthlyReport(
            final String id, final Integer year, final Integer month, final Integer page, final Integer pageSize
    ) {
        validator.validate(id);
        return getMonthlyEntriesReportUseCase.execute(year, Month.of(month), id, new Pagination(page, pageSize));
    }
}