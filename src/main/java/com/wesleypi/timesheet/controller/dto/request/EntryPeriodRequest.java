package com.wesleypi.timesheet.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntryPeriodRequest {
    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private String start;

    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private String end;
    private String description;
}
