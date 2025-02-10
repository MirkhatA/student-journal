package com.jdbc.starter.controller;

import com.jdbc.starter.database.dto.request.GroupRequest;
import com.jdbc.starter.database.dto.response.GroupResponse;
import com.jdbc.starter.services.GroupService;
import com.jdbc.starter.constants.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.jdbc.starter.constants.SwaggerConstants.GROUP_ID_DESCRIPTION;

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
    public ResponseEntity<List<GroupResponse>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @Operation(summary = SwaggerConstants.GET_GROUP_BY_ID_SUMMARY, description = SwaggerConstants.GET_GROUP_BY_ID_DESC)
    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getGroupById(
            @Parameter(description = GROUP_ID_DESCRIPTION, required = true) @PathVariable Long id) {
        return groupService.getGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = SwaggerConstants.CREATE_GROUP_SUMMARY, description = SwaggerConstants.CREATE_GROUP_DESC)
    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(@Valid @RequestBody GroupRequest request) {
        GroupResponse response = groupService.saveGroup(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = SwaggerConstants.UPDATE_GROUP_SUMMARY, description = SwaggerConstants.UPDATE_GROUP_DESC)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGroup(
            @Parameter(description = GROUP_ID_DESCRIPTION, required = true) @PathVariable Long id,
            @Valid @RequestBody GroupRequest request) {
        groupService.updateGroup(id, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = SwaggerConstants.DELETE_GROUP_SUMMARY, description = SwaggerConstants.DELETE_GROUP_DESC)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(
            @Parameter(description = GROUP_ID_DESCRIPTION, required = true) @PathVariable Long id) {
        boolean isDeleted = groupService.deleteGroup(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
