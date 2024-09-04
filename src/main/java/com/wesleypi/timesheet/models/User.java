package com.wesleypi.timesheet.models;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class User {
    private Long id;
    private Long groupId;
    private String name;
    private String email;
    private String password;
    private LocalDate startDate;
    private BigDecimal hourlyRate;
    private Long hourBank;
    private BigDecimal monthlyContract;
}
