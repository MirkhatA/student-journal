package com.jdbc.starter.controller;

import com.jdbc.starter.database.entity.Group;
import com.jdbc.starter.services.GroupService;
import com.jdbc.starter.util.constants.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.jdbc.starter.util.constants.SwaggerConstants.GROUP_ID_DESCRIPTION;

@Tag(name = SwaggerConstants.GROUPS_TAG, description = SwaggerConstants.GROUPS_DESCRIPTION)
@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @Operation(summary = SwaggerConstants.GET_ALL_GROUPS_SUMMARY, description = SwaggerConstants.GET_ALL_GROUPS_DESC)
    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @Operation(summary = SwaggerConstants.GET_GROUP_BY_ID_SUMMARY, description = SwaggerConstants.GET_GROUP_BY_ID_DESC)
    @GetMapping("/{id}")
    public Optional<Group> getGroupById(
            @Parameter(description = GROUP_ID_DESCRIPTION, required = true) @PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @Operation(summary = SwaggerConstants.CREATE_GROUP_SUMMARY, description = SwaggerConstants.CREATE_GROUP_DESC)
    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @Operation(summary = SwaggerConstants.UPDATE_GROUP_SUMMARY, description = SwaggerConstants.UPDATE_GROUP_DESC)
    @PutMapping("/{id}")
    public void updateGroup(
            @Parameter(description = GROUP_ID_DESCRIPTION, required = true) @PathVariable Long id,
            @RequestBody Group group) {
        group.setId(id);
        groupService.updateGroup(group);
    }

    @Operation(summary = SwaggerConstants.DELETE_GROUP_SUMMARY, description = SwaggerConstants.DELETE_GROUP_DESC)
    @DeleteMapping("/{id}")
    public boolean deleteGroup(
            @Parameter(description = GROUP_ID_DESCRIPTION, required = true) @PathVariable Long id) {
        return groupService.deleteGroup(id);
    }
}
