package com.wesleypi.timesheet.usecase.user;

import com.wesleypi.timesheet.controller.dto.request.user.UserCreateRequest;
import com.wesleypi.timesheet.controller.dto.response.auth.UserCreateResponse;
import com.wesleypi.timesheet.exception.ResourceConflictException;
import com.wesleypi.timesheet.exception.ResourceNotFoundException;
import com.wesleypi.timesheet.persistence.entity.GroupEntity;
import com.wesleypi.timesheet.persistence.repository.GroupRepository;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.wesleypi.timesheet.helper.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    final UserRepository userRepository;
    final GroupRepository groupRepository;
    final PasswordEncoder passwordEncoder;

    public UserCreateResponse execute(final UserCreateRequest userCreateRequest) {

        final var findUser = userRepository.findByEmail(userCreateRequest.getEmail());

        if (findUser.isPresent()) {
            throw new ResourceConflictException("Já existe um usuário com este email");
        }

        //TODO: change logic to find group using external id
        final var foundGroup = groupRepository.findByExternalId(userCreateRequest.getGroupId()).orElseThrow(() -> new ResourceNotFoundException("Grupo invalido"));

        final var encodedPassword = passwordEncoder.encode(userCreateRequest.getPassword());

        final var userEntity = USER_MAPPER.toUserEntity(userCreateRequest, foundGroup, encodedPassword);

        final var savedUser = userRepository.save(userEntity);

        return new UserCreateResponse(savedUser.getExternalId());
    }
}
