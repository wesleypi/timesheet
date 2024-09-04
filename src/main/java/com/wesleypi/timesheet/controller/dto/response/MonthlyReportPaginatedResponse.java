package com.wesleypi.timesheet.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyReportPaginatedResponse {
    private Pagination pagination;
    private Boolean closed;
    private Integer month;
    private Integer year;
    private String total;
    private Long balance;
    private List<DayResponse> days;
}
