package com.jdbc.starter.controller;

import com.jdbc.starter.database.dto.request.StudentRequest;
import com.jdbc.starter.database.dto.response.StudentResponse;
import com.jdbc.starter.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.jdbc.starter.constants.SwaggerConstants.CREATE_STUDENT_DESC;
import static com.jdbc.starter.constants.SwaggerConstants.CREATE_STUDENT_SUMMARY;
import static com.jdbc.starter.constants.SwaggerConstants.DELETE_STUDENT_DESC;
import static com.jdbc.starter.constants.SwaggerConstants.DELETE_STUDENT_SUMMARY;
import static com.jdbc.starter.constants.SwaggerConstants.FIND_STUDENTS_BY_NAME_DESC;
import static com.jdbc.starter.constants.SwaggerConstants.FIND_STUDENTS_BY_NAME_SUMMARY;
import static com.jdbc.starter.constants.SwaggerConstants.GET_ALL_STUDENTS_DESC;
import static com.jdbc.starter.constants.SwaggerConstants.GET_ALL_STUDENTS_SUMMARY;
import static com.jdbc.starter.constants.SwaggerConstants.GET_STUDENTS_BY_GROUP_DESC;
import static com.jdbc.starter.constants.SwaggerConstants.GET_STUDENTS_BY_GROUP_SUMMARY;
import static com.jdbc.starter.constants.SwaggerConstants.GET_STUDENT_BY_ID_DESC;
import static com.jdbc.starter.constants.SwaggerConstants.GET_STUDENT_BY_ID_SUMMARY;
import static com.jdbc.starter.constants.SwaggerConstants.GROUP_ID_DESCRIPTION;
import static com.jdbc.starter.constants.SwaggerConstants.NAME_TO_SEARCH_DESCRIPTION;
import static com.jdbc.starter.constants.SwaggerConstants.STUDENTS_DESCRIPTION;
import static com.jdbc.starter.constants.SwaggerConstants.STUDENTS_TAG;
import static com.jdbc.starter.constants.SwaggerConstants.STUDENT_ID_DESCRIPTION;
import static com.jdbc.starter.constants.SwaggerConstants.UPDATE_STUDENT_DESC;
import static com.jdbc.starter.constants.SwaggerConstants.UPDATE_STUDENT_SUMMARY;

@Tag(name = STUDENTS_TAG, description = STUDENTS_DESCRIPTION)
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = GET_ALL_STUDENTS_SUMMARY, description = GET_ALL_STUDENTS_DESC)
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @Operation(summary = GET_STUDENT_BY_ID_SUMMARY, description = GET_STUDENT_BY_ID_DESC)
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(
            @Parameter(description = STUDENT_ID_DESCRIPTION, required = true)
            @PathVariable Long id)
    {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = GET_STUDENTS_BY_GROUP_SUMMARY, description = GET_STUDENTS_BY_GROUP_DESC)
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<StudentResponse>> getStudentsByGroup(
            @Parameter(description = GROUP_ID_DESCRIPTION, required = true)
            @PathVariable Long groupId)
    {
        return ResponseEntity.ok(studentService.getStudentsByGroup(groupId));
    }

    @Operation(summary = FIND_STUDENTS_BY_NAME_SUMMARY,
            description = FIND_STUDENTS_BY_NAME_DESC)
    @GetMapping("/search")
    public ResponseEntity<List<StudentResponse>> findStudentsByName(
            @Parameter(description = NAME_TO_SEARCH_DESCRIPTION, required = true)
            @RequestParam String name
    ) {
        return ResponseEntity.ok(studentService.findStudentsByName(name));
    }

    @Operation(summary = CREATE_STUDENT_SUMMARY,
            description = CREATE_STUDENT_DESC)
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest request) {
        StudentResponse response = studentService.saveStudent(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = UPDATE_STUDENT_SUMMARY,
            description = UPDATE_STUDENT_DESC)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(
            @Parameter(description = STUDENT_ID_DESCRIPTION, required = true)
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request
    ) {
        studentService.updateStudent(id, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = DELETE_STUDENT_SUMMARY,
            description = DELETE_STUDENT_DESC)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = STUDENT_ID_DESCRIPTION, required = true) @PathVariable Long id
    ) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get sorted list of students", description = "Sort students by first name or last name")
    public ResponseEntity<List<StudentResponse>> getSortedStudents(
            @RequestParam(required = false, defaultValue = "firstName") String sort,
            @RequestParam(required = false, defaultValue = "asc") String order) {
        return ResponseEntity.ok(studentService.getSortedStudents(sort, order));
    }
}
