package com.wesleypi.timesheet.controller;

import com.wesleypi.timesheet.controller.dto.response.ExceptionResponse;
import com.wesleypi.timesheet.controller.dto.request.holiday.HolidayCreateRequest;
import com.wesleypi.timesheet.controller.dto.request.holiday.HolidaysPutRequest;
import com.wesleypi.timesheet.controller.dto.response.HolidaysPaginatedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "holiday", description = "Resources for holiday management")
@RequestMapping("/holiday")
public interface HolidayController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "List holidays",
            description = "Get a list of holidays"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HolidaysPaginatedResponse.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            examples = {

                                            }
                                    ),
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = {@Content(mediaType = "application/json", examples = {@ExampleObject(name = "INTERNAL_500", value = "{\"code\":\"INTERNAL_500\",\"message\":\"Internal Server Error.\"}")})}
                    ),
            }
    )
    HolidaysPaginatedResponse getHolidays(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer pageSize);

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Create holidays",
            description = "Insert holidays into the api"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
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
    ResponseEntity<Object> postHolidays(@RequestBody HolidayCreateRequest holidayCreateRequest);

    @PutMapping("/{year}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Update holidays",
            description = "update holidays of a year"
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
    ResponseEntity<Object> updateHoliday(@PathVariable final int year, @RequestBody final HolidaysPutRequest request);
}

