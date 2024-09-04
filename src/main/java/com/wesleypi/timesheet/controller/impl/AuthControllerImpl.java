package com.wesleypi.timesheet.controller.impl;

import com.wesleypi.timesheet.controller.AuthController;
import com.wesleypi.timesheet.controller.dto.request.auth.RecoverPasswordRequest;
import com.wesleypi.timesheet.controller.dto.request.auth.ChangePasswordRequest;
import com.wesleypi.timesheet.controller.dto.request.auth.RefreshTokenRequest;
import com.wesleypi.timesheet.controller.dto.request.auth.CreateTokenRequest;
import com.wesleypi.timesheet.controller.dto.response.auth.CreateTokenResponse;
import com.wesleypi.timesheet.usecase.auth.ChangePasswordUseCase;
import com.wesleypi.timesheet.usecase.auth.RefreshTokenUseCase;
import com.wesleypi.timesheet.usecase.auth.CreateTokenUseCase;
import com.wesleypi.timesheet.usecase.auth.PasswordRecoveryUseCase;
import com.wesleypi.timesheet.validation.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final RequestValidator requestValidator;
    private final CreateTokenUseCase createTokenUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final PasswordRecoveryUseCase passwordRecoveryUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    public CreateTokenResponse createToken(@RequestBody CreateTokenRequest createTokenRequest) {
        requestValidator.validate(createTokenRequest);
        return createTokenUseCase.execute(createTokenRequest);
    }

    public CreateTokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        requestValidator.validate(refreshTokenRequest);
        return refreshTokenUseCase.execute(refreshTokenRequest);
    }

    public ResponseEntity<Object> postRecoverPassword(@RequestBody RecoverPasswordRequest request) {
        requestValidator.validate(request);
        passwordRecoveryUseCase.execute(request);
        return ResponseEntity.status(204).build();
    }

    public ResponseEntity<Object> putRecoverPassword(@RequestBody ChangePasswordRequest request) {
        requestValidator.validate(request);
        changePasswordUseCase.execute(request);
        return ResponseEntity.status(204).build();
    }
}
