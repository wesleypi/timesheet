package com.wesleypi.timesheet.models;

import lombok.Data;
import java.time.LocalDate;

@Data
public class HourBank {
    private Long id;
    private Long userId;
    private LocalDate date;
    private Long hours;
    private String description;
}
