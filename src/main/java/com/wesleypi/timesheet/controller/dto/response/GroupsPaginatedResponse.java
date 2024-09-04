package com.wesleypi.timesheet.controller.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupsPaginatedResponse {
    Pagination pagination;

    List<GroupResponse> groups;
}
