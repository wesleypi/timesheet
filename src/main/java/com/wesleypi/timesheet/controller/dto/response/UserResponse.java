package com.wesleypi.timesheet.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String email;
    private String team;
    private BigDecimal hourValue;
    private Boolean hasBankHours;
    private BigDecimal contractTotal;
    private String groupId;
    private String groupName;
    private LocalDate startDate;
}
