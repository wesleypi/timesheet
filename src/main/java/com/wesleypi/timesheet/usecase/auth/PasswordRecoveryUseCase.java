package com.wesleypi.timesheet.usecase.auth;

import com.wesleypi.timesheet.controller.dto.request.auth.RecoverPasswordRequest;
import com.wesleypi.timesheet.exception.ResourceNotFoundException;
import com.wesleypi.timesheet.persistence.repository.PasswordRecoveryRepository;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import com.wesleypi.timesheet.persistence.entity.PasswordRecoveryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryUseCase {

    private final PasswordRecoveryRepository passwordRecoveryRepository;
    private final UserRepository userRepository;

    public void execute(RecoverPasswordRequest recoverRequest) {
        final var user = userRepository.findByEmail(recoverRequest.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        final var recoveryEntity = PasswordRecoveryEntity.builder()
                .user(user)
                .code(generateRandomCode())
                .build();

        passwordRecoveryRepository.save(recoveryEntity);

        //TODO send email with code
    }

    private String generateRandomCode() {
        return String.format("%06d", new SecureRandom().nextInt(1_000_000));
    }
}
