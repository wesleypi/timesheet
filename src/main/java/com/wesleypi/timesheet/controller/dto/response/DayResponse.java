package com.wesleypi.timesheet.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayResponse {
    private String date;
    private Boolean businessDay;
    private List<PeriodResponse> period;
    private String total;
}
