package com.wesleypi.timesheet.usecase.user;

import com.wesleypi.timesheet.controller.dto.response.UserPaginatedResponse;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.wesleypi.timesheet.helper.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class GetAllUsersUseCase {
    private final UserRepository userRepository;

    public UserPaginatedResponse execute(final Integer page, final Integer pageSize) {
        final var foundUser = userRepository.findAll(PageRequest.of(page, pageSize, Sort.by("startDate").descending()));

        return USER_MAPPER.toUsersPaginatedResponse(foundUser);


    }
}
