package com.wesleypi.timesheet.usecase.user;

import com.wesleypi.timesheet.controller.dto.response.UserResponse;
import com.wesleypi.timesheet.exception.ResourceNotFoundException;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.wesleypi.timesheet.helper.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCase {
    private final UserRepository userRepository;

    public UserResponse execute(String externalId) {
        final var foundUser = userRepository.findByExternalId(externalId).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));

        return USER_MAPPER.toResponse(foundUser);
    }
}
