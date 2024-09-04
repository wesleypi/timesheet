package com.wesleypi.timesheet.helper.mapper;

import com.wesleypi.timesheet.controller.dto.request.user.UpdateUserRequest;
import com.wesleypi.timesheet.controller.dto.request.user.UserCreateRequest;
import com.wesleypi.timesheet.controller.dto.response.Pagination;
import com.wesleypi.timesheet.controller.dto.response.UserPaginatedResponse;
import com.wesleypi.timesheet.controller.dto.response.UserResponse;
import com.wesleypi.timesheet.persistence.entity.GroupEntity;
import com.wesleypi.timesheet.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(final UpdateUserRequest updateUserRequest);

    @Mapping(target = "hasBankHours", expression = "java(hasBankHours(userEntity.getHoursBank()))")
    @Mapping(target = "groupId", source = "group.externalId")
    @Mapping(target = "groupName", source = "group.name")
    UserResponse toResponse(final UserEntity userEntity);

    default boolean hasBankHours(Long hoursBank) {
        return hoursBank != 0L;
    }

    @Mapping(target = "name", source = "userRequest.name")
    @Mapping(target = "email", source = "userRequest.email")
    @Mapping(target = "startDate", source = "userRequest.startDate")
    @Mapping(target = "team", source = "userRequest.team")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "hourValue", source = "userRequest.hourValue")
    @Mapping(target = "contractTotal", source = "userRequest.contractTotal")
    @Mapping(target = "group", source = "group")
    UserEntity toUserEntity(UserCreateRequest userRequest, GroupEntity group, String password);

    default UserPaginatedResponse toUsersPaginatedResponse(final Page<UserEntity> users) {
        var userPaginatedResponse = new UserPaginatedResponse();

        if (isNull(users)) {
            return userPaginatedResponse;
        }

        final var pageable = users.getPageable();
        userPaginatedResponse.setPagination(new Pagination(pageable.getPageNumber(), pageable.getPageSize(), users.getTotalElements()));

        if (!users.hasContent()) {
            return userPaginatedResponse;
        }

        userPaginatedResponse.setUsers(users.getContent().stream().map(this::toResponse).toList());

        return userPaginatedResponse;
    }

    default UserEntity updateEntity(@NonNull UserEntity originEntity, @NonNull UserEntity newEntity) {
        return UserEntity.builder()
                .id(originEntity.getId())
                .externalId(originEntity.getExternalId())
                .password(originEntity.getPassword())
                .group(originEntity.getGroup())
                .name(ofNullable(newEntity.getName()).orElse(originEntity.getName()))
                .email(ofNullable(newEntity.getEmail()).orElse(originEntity.getEmail()))
                .team(ofNullable(newEntity.getTeam()).orElse(originEntity.getTeam()))
                .startDate(ofNullable(newEntity.getStartDate()).orElse(originEntity.getStartDate()))
                .contractTotal(ofNullable(newEntity.getContractTotal()).orElse(originEntity.getContractTotal()))
                .hourValue(ofNullable(newEntity.getHourValue()).orElse(originEntity.getHourValue()))
                .build();
    }
}

