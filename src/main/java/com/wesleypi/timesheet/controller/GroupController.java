package com.wesleypi.timesheet.controller;

import com.wesleypi.timesheet.controller.dto.response.GroupsPaginatedResponse;
import com.wesleypi.timesheet.controller.dto.response.HolidaysPaginatedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
@Tag(name = "group", description = "Resources for group management")
public interface GroupController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "List group",
            description = "Get a list of groups"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GroupsPaginatedResponse.class))}),
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
    GroupsPaginatedResponse getGroup(@RequestParam(defaultValue = "0") final Integer page, @RequestParam(defaultValue = "20") final Integer pageSize);
}