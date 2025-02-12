package com.jdbc.starter.services;

import com.jdbc.starter.database.dto.request.GroupRequest;
import com.jdbc.starter.database.dto.response.GroupResponse;
import com.jdbc.starter.mapper.GroupMapper;
import com.jdbc.starter.repository.GroupRepository;
import com.jdbc.starter.database.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<GroupResponse> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(GroupMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<GroupResponse> getGroupById(Long id) {
        return groupRepository.findById(id)
                .map(GroupMapper::toResponse);
    }

    public GroupResponse saveGroup(GroupRequest request) {
        Group group = GroupMapper.toEntity(request);
        Group savedGroup = groupRepository.save(group);
        return GroupMapper.toResponse(savedGroup);
    }

    public void updateGroup(Long id, GroupRequest request) {
        Group group = GroupMapper.toEntity(request);
        group.setId(id);
        groupRepository.update(group);
    }

    public boolean deleteGroup(Long id) {
        return groupRepository.delete(id);
    }
}
