package com.wesleypi.timesheet.usecase.auth;

import com.wesleypi.timesheet.controller.dto.request.auth.ChangePasswordRequest;
import com.wesleypi.timesheet.exception.ResourceNotFoundException;
import com.wesleypi.timesheet.persistence.repository.PasswordRecoveryRepository;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordUseCase {

    private final PasswordRecoveryRepository passwordRecoveryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(final ChangePasswordRequest changePasswordRequest) {
        final var recoveryEntity = passwordRecoveryRepository.findByCode(changePasswordRequest.getCode()).orElseThrow(() -> new ResourceNotFoundException("Codigo de recupecao invalido"));

        final var foundUser = userRepository.findById(recoveryEntity.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));

        final var newPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());

        foundUser.setPassword(newPassword);

        userRepository.save(foundUser);

        passwordRecoveryRepository.delete(recoveryEntity);
    }
}
