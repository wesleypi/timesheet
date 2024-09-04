package com.wesleypi.timesheet.helper.mapper;

import com.wesleypi.timesheet.controller.dto.response.Pagination;
import com.wesleypi.timesheet.controller.dto.response.GroupResponse;
import com.wesleypi.timesheet.controller.dto.response.GroupsPaginatedResponse;
import com.wesleypi.timesheet.persistence.entity.GroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import static java.util.Objects.isNull;

@Mapper
public interface GroupMapper {
    GroupMapper GROUP_MAPPER = Mappers.getMapper(GroupMapper.class);

    GroupResponse toResponse(GroupEntity group);

    default GroupsPaginatedResponse toGroupsPaginatedResponse(final Page<GroupEntity> groups) {
        var groupsPaginatedResponse = new GroupsPaginatedResponse();

        if (isNull(groups)) {
            return groupsPaginatedResponse;
        }

        final var pageable = groups.getPageable();
        groupsPaginatedResponse.setPagination(new Pagination(pageable.getPageNumber(), pageable.getPageSize(), groups.getTotalElements()));

        if (!groups.hasContent()) {
            return groupsPaginatedResponse;
        }

        groupsPaginatedResponse.setGroups(groups.getContent().stream().map(this::toResponse).toList());

        return groupsPaginatedResponse;
    }
}

