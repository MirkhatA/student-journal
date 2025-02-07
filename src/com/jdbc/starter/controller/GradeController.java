package com.jdbc.starter.controller;

import com.jdbc.starter.database.entity.Grade;
import com.jdbc.starter.services.GradeService;
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

import static com.jdbc.starter.util.constants.SwaggerConstants.GRADE_ID_DESCRIPTION;

@Tag(name = SwaggerConstants.GRADES_TAG, description = SwaggerConstants.GRADES_DESCRIPTION)
@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @Operation(summary = SwaggerConstants.GET_ALL_GRADES_SUMMARY, description = SwaggerConstants.GET_ALL_GRADES_DESC)
    @GetMapping
    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    @Operation(summary = SwaggerConstants.GET_GRADE_BY_ID_SUMMARY, description = SwaggerConstants.GET_GRADE_BY_ID_DESC)
    @GetMapping("/{id}")
    public Optional<Grade> getGradeById(
            @Parameter(description = GRADE_ID_DESCRIPTION, required = true) @PathVariable Long id) {
        return gradeService.getGradeById(id);
    }

    @Operation(summary = SwaggerConstants.CREATE_GRADE_SUMMARY, description = SwaggerConstants.CREATE_GRADE_DESC)
    @PostMapping
    public Grade createGrade(@RequestBody Grade grade) {
        return gradeService.saveGrade(grade);
    }

    @Operation(summary = SwaggerConstants.UPDATE_GRADE_SUMMARY, description = SwaggerConstants.UPDATE_GRADE_DESC)
    @PutMapping("/{id}")
    public void updateGrade(
            @Parameter(description = GRADE_ID_DESCRIPTION, required = true) @PathVariable Long id,
            @RequestBody Grade grade) {
        grade.setId(id);
        gradeService.updateGrade(grade);
    }

    @Operation(summary = SwaggerConstants.DELETE_GRADE_SUMMARY, description = SwaggerConstants.DELETE_GRADE_DESC)
    @DeleteMapping("/{id}")
    public boolean deleteGrade(
            @Parameter(description = GRADE_ID_DESCRIPTION, required = true) @PathVariable Long id) {
        return gradeService.deleteGrade(id);
    }
}
