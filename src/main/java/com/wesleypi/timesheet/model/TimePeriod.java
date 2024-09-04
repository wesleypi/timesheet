package com.wesleypi.timesheet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimePeriod {
    private LocalTime start;
    private LocalTime end;
    private String description;
}
