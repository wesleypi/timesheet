package com.wesleypi.timesheet.usecase.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wesleypi.timesheet.controller.dto.request.auth.CreateTokenRequest;
import com.wesleypi.timesheet.controller.dto.response.auth.CreateTokenResponse;
import com.wesleypi.timesheet.exception.UnauthorizedException;
import com.wesleypi.timesheet.persistence.repository.RefreshTokenRepository;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import com.wesleypi.timesheet.persistence.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateTokenUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.secret}")
    private String secret;

    public CreateTokenResponse execute(final CreateTokenRequest createTokenRequest) {
        var user = userRepository.findByEmail(createTokenRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("email ou senha estão incorretos"));

        final var passwordMatches = passwordEncoder.matches(createTokenRequest.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new UnauthorizedException();
        }

        var roles = List.of(user.getGroup().getName());

        final var tokenExpiresIn = Instant.now().plus(Duration.ofMinutes(240));

        final var token = JWT.create()
                .withIssuer(String.valueOf(user.getExternalId()))
                .withExpiresAt(tokenExpiresIn)
                .withClaim("roles", roles)
                .withSubject(String.valueOf(user.getExternalId()))
                .sign(Algorithm.HMAC256(secret));

        final var refreshTokenExpiresIn = Instant.now().plus(Duration.ofMinutes(720));

        final var refreshToken = JWT.create()
                .withIssuer(user.getExternalId())
                .withIssuedAt(Date.from(Instant.now()))
                .sign(Algorithm.HMAC256(secret));

        final var refreshTokenEntity = RefreshTokenEntity.builder()
                .user(user)
                .refreshToken(refreshToken)
                .expiresAt(refreshTokenExpiresIn)
                .build();

        refreshTokenRepository.save(refreshTokenEntity);

        return CreateTokenResponse.builder()
                .accessToken(token)
                .expiresIn(tokenExpiresIn.toEpochMilli())
                .refreshToken(refreshTokenEntity.getRefreshToken())
                .tokenType("Bearer")
                .build();
    }

}
