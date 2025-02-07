package com.jdbc.starter.controller;

import com.jdbc.starter.database.entity.Student;
import com.jdbc.starter.services.StudentService;
import com.jdbc.starter.util.constants.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.Optional;

import static com.jdbc.starter.util.constants.SwaggerConstants.*;

@Tag(name = SwaggerConstants.STUDENTS_TAG, description = SwaggerConstants.STUDENTS_DESCRIPTION)
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = SwaggerConstants.GET_ALL_STUDENTS_SUMMARY,
            description = SwaggerConstants.GET_ALL_STUDENTS_DESC)
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = SwaggerConstants.GET_STUDENT_BY_ID_SUMMARY,
            description = SwaggerConstants.GET_STUDENT_BY_ID_DESC)
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(
            @Parameter(description = STUDENT_ID_DESCRIPTION, required = true) @PathVariable Long id
    ) {
        return studentService.getStudentById(id);
    }

    @Operation(summary = SwaggerConstants.GET_STUDENTS_BY_GROUP_SUMMARY,
            description = SwaggerConstants.GET_STUDENTS_BY_GROUP_DESC)
    @GetMapping("/group/{groupId}")
    public List<Student> getStudentsByGroup(
            @Parameter(description = GROUP_ID_DESCRIPTION, required = true) @PathVariable Long groupId
    ) {
        return studentService.getStudentsByGroup(groupId);
    }

    @Operation(summary = SwaggerConstants.FIND_STUDENTS_BY_NAME_SUMMARY,
            description = SwaggerConstants.FIND_STUDENTS_BY_NAME_DESC)
    @GetMapping("/search")
    public List<Student> findStudentsByName(
            @Parameter(description = NAME_TO_SEARCH_DESCRIPTION, required = true) @RequestParam String name
    ) {
        return studentService.findStudentsByName(name);
    }

    @Operation(summary = SwaggerConstants.CREATE_STUDENT_SUMMARY,
            description = SwaggerConstants.CREATE_STUDENT_DESC)
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @Operation(summary = SwaggerConstants.UPDATE_STUDENT_SUMMARY,
            description = SwaggerConstants.UPDATE_STUDENT_DESC)
    @PutMapping("/{id}")
    public void updateStudent(
            @Parameter(description = STUDENT_ID_DESCRIPTION, required = true)
            @PathVariable Long id, @RequestBody Student student
    ) {
        student.setId(id);
        studentService.updateStudent(student);
    }

    @Operation(summary = SwaggerConstants.DELETE_STUDENT_SUMMARY,
            description = SwaggerConstants.DELETE_STUDENT_DESC)
    @DeleteMapping("/{id}")
    public boolean deleteStudent(
            @Parameter(description = STUDENT_ID_DESCRIPTION, required = true) @PathVariable Long id
    ) {
        return studentService.deleteStudent(id);
    }
}
