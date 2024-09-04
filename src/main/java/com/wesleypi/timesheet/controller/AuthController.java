package com.wesleypi.timesheet.controller;

import com.wesleypi.timesheet.controller.dto.request.auth.CreateTokenRequest;
import com.wesleypi.timesheet.controller.dto.request.auth.RecoverPasswordRequest;
import com.wesleypi.timesheet.controller.dto.request.auth.ChangePasswordRequest;
import com.wesleypi.timesheet.controller.dto.request.auth.RefreshTokenRequest;
import com.wesleypi.timesheet.controller.dto.response.auth.CreateTokenResponse;
import com.wesleypi.timesheet.controller.dto.response.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Resources for managing authentications.")
@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Create token",
            description = "Create token to access api"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User successfully generated.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreateTokenResponse.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ExceptionResponse.class),
                                            examples = {@ExampleObject(name = "INVALID_FIELDS.", value = "{\"code\":\"INVALID FIELDS.\",\"message\":\"Invalid/required fields\"}")}
                                    ),
                            }
                    )
            }
    )
    CreateTokenResponse createToken(@RequestBody CreateTokenRequest createTokenRequest);

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Refresh token endpoints.",
            description = "Refresh old token."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User successfully refreshed.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreateTokenResponse.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ExceptionResponse.class),
                                            examples = {@ExampleObject(name = "INVALID_FIELDS.", value = "{\"code\":\"INVALID FIELDS.\",\"message\":\"Invalid/required fields\"}")}
                                    ),
                            }
                    )
            }
    )
    CreateTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    @PostMapping("recover-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Recover password.",
            description = "Recover password."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ExceptionResponse.class),
                                            examples = {@ExampleObject(name = "INVALID_FIELDS.", value = "{\"code\":\"INVALID FIELDS.\",\"message\":\"Invalid/required fields\"}")}
                                    ),
                            }
                    )
            }
    )
    ResponseEntity<Object> postRecoverPassword(RecoverPasswordRequest authentication);

    @PutMapping("recover-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Change password.",
            description = "Change password."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ExceptionResponse.class),
                                            examples = {@ExampleObject(name = "INVALID_FIELDS.", value = "{\"code\":\"INVALID FIELDS.\",\"message\":\"Invalid/required fields\"}")}
                                    ),
                            }
                    )
            }
    )
    ResponseEntity<Object> putRecoverPassword(ChangePasswordRequest request);
}