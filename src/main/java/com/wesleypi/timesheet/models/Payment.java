package com.wesleypi.timesheet.models;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.time.Month;

@Data
public class Payment {
    private int id;
    private int userId;
    private Month month;
    private Year year;
    private Long totalHours;
    private LocalDate paymentDate;
    private BigDecimal hourlyRate;
    private BigDecimal totalAmount;
    private Long currentHourBank;
}
