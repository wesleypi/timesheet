package com.wesleypi.timesheet.controller;

import com.wesleypi.timesheet.controller.dto.request.user.UpdateUserHoursBankRequest;
import com.wesleypi.timesheet.controller.dto.request.user.UpdateUserPasswordRequest;
import com.wesleypi.timesheet.controller.dto.request.user.UpdateUserRequest;
import com.wesleypi.timesheet.controller.dto.request.user.UserCreateRequest;
import com.wesleypi.timesheet.controller.dto.response.MonthlyReportPaginatedResponse;
import com.wesleypi.timesheet.controller.dto.response.PatchUserMonthlyReportResponse;
import com.wesleypi.timesheet.controller.dto.response.UserPaginatedResponse;
import com.wesleypi.timesheet.controller.dto.response.UserResponse;
import com.wesleypi.timesheet.controller.dto.response.auth.UserCreateResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "Resources for user management")
@RestController
@RequestMapping("/user")
public interface UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserCreateResponse.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(mediaType = "application/json", examples = {@ExampleObject(name = "INTERNAL_500", value = "{\"code\":\"INTERNAL_500\",\"message\":\"Internal Server Error.\"}")})}
                    ),
            }
    )
    UserCreateResponse createUser(@RequestBody final UserCreateRequest request);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserPaginatedResponse.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(mediaType = "application/json", examples = {@ExampleObject(name = "INTERNAL_500", value = "{\"code\":\"INTERNAL_500\",\"message\":\"Internal Server Error.\"}")})}
                    ),
            }
    )
    UserPaginatedResponse getAllUsers(@RequestParam(defaultValue = "0") final Integer page,
                                      @RequestParam(defaultValue = "20") final Integer pageSize);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserCreateResponse.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(mediaType = "application/json", examples = {@ExampleObject(name = "INTERNAL_500", value = "{\"code\":\"INTERNAL_500\",\"message\":\"Internal Server Error.\"}")})}
                    ),
            }
    )
    UserResponse getUserById(@PathVariable @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$") final String id);

    @PutMapping("/{id}/password")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(mediaType = "application/json", examples = {@ExampleObject(name = "INTERNAL_500", value = "{\"code\":\"INTERNAL_500\",\"message\":\"Internal Server Error.\"}")})}
                    ),
            }
    )
    ResponseEntity<Object> putUserPassword(@PathVariable @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$") final String id,
                                           @RequestBody final UpdateUserPasswordRequest updateUserPasswordRequest);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(mediaType = "application/json", examples = {@ExampleObject(name = "INTERNAL_500", value = "{\"code\":\"INTERNAL_500\",\"message\":\"Internal Server Error.\"}")})}
                    ),
            }
    )
    ResponseEntity<Object> putUserById(@PathVariable @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$") final String id, final UpdateUserRequest updateUserRequest);

    @PatchMapping("{id}/report/{year}/{month}/closed")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatchUserMonthlyReportResponse.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(mediaType = "application/json", examples = {@ExampleObject(name = "INTERNAL_500", value = "{\"code\":\"INTERNAL_500\",\"message\":\"Internal Server Error.\"}")})}
                    ),
            }
    )
    PatchUserMonthlyReportResponse patchReport(@PathVariable("id")
                                               @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$") final String id, @PathVariable("year") final Integer year, @PathVariable("month") final Integer month);
    @PatchMapping("{id}/bank")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(mediaType = "application/json", examples = {@ExampleObject(name = "INTERNAL_500", value = "{\"code\":\"INTERNAL_500\",\"message\":\"Internal Server Error.\"}")})}
                    ),
            }
    )
    ResponseEntity<Object> patchUserHoursBank(@PathVariable("id")
                                               @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$") final String id, @RequestBody final UpdateUserHoursBankRequest updateUserHoursBank);
    @GetMapping("{id}/report/{year}/{month}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonthlyReportPaginatedResponse.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(mediaType = "application/json", examples = {@ExampleObject(name = "INTERNAL_500", value = "{\"code\":\"INTERNAL_500\",\"message\":\"Internal Server Error.\"}")})}
                    ),
            }
    )
    MonthlyReportPaginatedResponse getMonthlyReport(@PathVariable("id")
                                               @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$") final String id,
                                                    @PathVariable("year") final Integer year,
                                                    @PathVariable("month") final Integer month,
                                                    @RequestParam(defaultValue = "0") final Integer page,
                                                    @RequestParam(defaultValue = "20") final Integer pageSize);
}