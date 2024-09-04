package com.wesleypi.timesheet.controller.impl;

import com.wesleypi.timesheet.controller.GroupController;

import com.wesleypi.timesheet.controller.dto.response.GroupsPaginatedResponse;
import com.wesleypi.timesheet.usecase.group.GetGroupsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupControllerImpl implements GroupController {

    private final GetGroupsUseCase getGroupsUseCase;

    @Override
    public GroupsPaginatedResponse getGroup(final Integer page,final Integer pageSize) {
        return getGroupsUseCase.execute(page, pageSize);
    }
}