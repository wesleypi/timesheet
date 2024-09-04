package com.wesleypi.timesheet.usecase.group;

import com.wesleypi.timesheet.controller.dto.response.GroupsPaginatedResponse;
import com.wesleypi.timesheet.persistence.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.wesleypi.timesheet.helper.mapper.GroupMapper.GROUP_MAPPER;

@Service
@RequiredArgsConstructor
public class GetGroupsUseCase {

    final GroupRepository groupRepository;

    public GroupsPaginatedResponse execute(final Integer page, final Integer pageSize) {
        final var pagedGroups = groupRepository.findAll(PageRequest.of(page, pageSize, Sort.by("id").ascending()));

        return GROUP_MAPPER.toGroupsPaginatedResponse(pagedGroups);
    }
}
