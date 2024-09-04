package com.wesleypi.timesheet.models;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class Entry {
    private Long id;
    private Long userId;
    private LocalDate date;
    private Boolean holiday;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<Period> period;
    private String description;
}
