package com.jdbc.starter.controller;

import com.jdbc.starter.constants.SwaggerConstants;
import com.jdbc.starter.database.dto.request.GradeRequest;
import com.jdbc.starter.database.dto.response.GradeResponse;
import com.jdbc.starter.services.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jdbc.starter.constants.SwaggerConstants.GET_ALL_GRADES_BY_STUDENT_ID_DESC;
import static com.jdbc.starter.constants.SwaggerConstants.GET_ALL_GRADES_BY_STUDENT_ID_SUMMARY;
import static com.jdbc.starter.constants.SwaggerConstants.GRADE_ID_DESCRIPTION;
import static com.jdbc.starter.constants.SwaggerConstants.STUDENT_ID_DESCRIPTION;

@Tag(name = SwaggerConstants.GRADES_TAG, description = SwaggerConstants.GRADES_DESCRIPTION)
@RestController
@RequestMapping("/v1/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @Operation(summary = SwaggerConstants.GET_ALL_GRADES_SUMMARY, description = SwaggerConstants.GET_ALL_GRADES_DESC)
    @GetMapping
    public ResponseEntity<List<GradeResponse>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    @Operation(summary = SwaggerConstants.GET_GRADE_BY_ID_SUMMARY, description = SwaggerConstants.GET_GRADE_BY_ID_DESC)
    @GetMapping("/{id}")
    public ResponseEntity<GradeResponse> getGradeById(
            @Parameter(description = GRADE_ID_DESCRIPTION, required = true) @PathVariable Long id) {
        return gradeService.getGradeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = SwaggerConstants.CREATE_GRADE_SUMMARY, description = SwaggerConstants.CREATE_GRADE_DESC)
    @PostMapping
    public ResponseEntity<GradeResponse> createGrade(@Valid @RequestBody GradeRequest request) {
        GradeResponse response = gradeService.saveGrade(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = SwaggerConstants.UPDATE_GRADE_SUMMARY, description = SwaggerConstants.UPDATE_GRADE_DESC)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGrade(
            @Parameter(description = GRADE_ID_DESCRIPTION, required = true) @PathVariable Long id,
            @Valid @RequestBody GradeRequest request) {
        gradeService.updateGrade(id, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = SwaggerConstants.DELETE_GRADE_SUMMARY, description = SwaggerConstants.DELETE_GRADE_DESC)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(
            @Parameter(description = GRADE_ID_DESCRIPTION, required = true) @PathVariable Long id) {
        boolean isDeleted = gradeService.deleteGrade(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = GET_ALL_GRADES_BY_STUDENT_ID_SUMMARY, description = GET_ALL_GRADES_BY_STUDENT_ID_DESC)
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeResponse>> getGradesByStudentId(
            @Parameter(description = STUDENT_ID_DESCRIPTION, required = true) @PathVariable int studentId) {
        List<GradeResponse> grades = gradeService.getAllGradesByStudentId(studentId);
        return ResponseEntity.ok(grades);
    }

}
