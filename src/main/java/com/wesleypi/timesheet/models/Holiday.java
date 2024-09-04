package com.wesleypi.timesheet.models;


import lombok.Data;

import java.time.LocalDate;
import java.time.Year;

@Data
public class Holiday {
    private int id;
    private Year year;
    private LocalDate date;
}
