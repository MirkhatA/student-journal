package com.jdbc.starter.mapper;

import com.jdbc.starter.database.dto.request.GroupRequest;
import com.jdbc.starter.database.dto.response.GroupResponse;
import com.jdbc.starter.database.entity.Group;

import java.time.LocalDateTime;

public class GroupMapper {

    public static Group toEntity(GroupRequest request) {
        return new Group(
                null,
                request.getName(),
                LocalDateTime.now()
        );
    }

    public static GroupResponse toResponse(Group group) {
        return new GroupResponse(
                group.getId(),
                group.getName(),
                group.getCreatedAt()
        );
    }
}
