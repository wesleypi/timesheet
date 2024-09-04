package com.wesleypi.timesheet.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatchUserMonthlyReportResponse {
    private String name;
    private String team;
    private Boolean bankHours;
    private Integer balanceBankHours;
    private CalendarResponse calendar;
    private PayResponse pay;
}
