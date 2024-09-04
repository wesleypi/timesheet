package com.wesleypi.timesheet.usecase.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wesleypi.timesheet.controller.dto.request.auth.RefreshTokenRequest;
import com.wesleypi.timesheet.controller.dto.response.auth.CreateTokenResponse;
import com.wesleypi.timesheet.exception.TimeoutUserConnectionException;
import com.wesleypi.timesheet.persistence.repository.RefreshTokenRepository;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefreshTokenUseCase {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secret;

    public CreateTokenResponse execute(final RefreshTokenRequest request) {
        final var refreshTokenEntity = refreshTokenRepository.findByRefreshToken(request.getRefreshToken()).orElseThrow(() -> new RuntimeException("declared token does not exist"));

        if (refreshTokenEntity.getExpiresAt().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshTokenEntity);
            throw new TimeoutUserConnectionException("Token expired");
        }
        final var user = userRepository.findById(refreshTokenEntity.getUser().getId()).orElseThrow();

        var roles = List.of(user.getGroup().getName());

        final var tokenExpiresIn = Instant.now().plus(Duration.ofMinutes(720));

        final var accessToken = JWT.create()
                .withIssuer(String.valueOf(user.getExternalId()))
                .withExpiresAt(tokenExpiresIn)
                .withClaim("roles", roles)
                .withSubject(String.valueOf(user.getExternalId()))
                .sign(Algorithm.HMAC256(secret));

        return CreateTokenResponse.builder()
                .accessToken(accessToken)
                .expiresIn(refreshTokenEntity.getExpiresAt().toEpochMilli()).refreshToken(request.getRefreshToken())
                .tokenType("Bearer").build();
    }
}
