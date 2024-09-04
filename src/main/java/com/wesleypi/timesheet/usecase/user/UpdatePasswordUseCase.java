package com.wesleypi.timesheet.usecase.user;

import com.wesleypi.timesheet.controller.dto.request.auth.ChangePasswordRequest;
import com.wesleypi.timesheet.controller.dto.request.user.UpdateUserPasswordRequest;
import com.wesleypi.timesheet.exception.ResourceNotFoundException;
import com.wesleypi.timesheet.persistence.repository.PasswordRecoveryRepository;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePasswordUseCase {

    private final PasswordRecoveryRepository passwordRecoveryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(final String externalId, final UpdateUserPasswordRequest updateUserPasswordRequest) {
        final var foundUser = userRepository.findByExternalId(externalId).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));

        final var newPassword = passwordEncoder.encode(updateUserPasswordRequest.getPassword());

        foundUser.setPassword(newPassword);

        userRepository.save(foundUser);
    }
}
