package com.jdbc.starter.services;

import com.jdbc.starter.database.dto.request.StudentRequest;
import com.jdbc.starter.database.dto.response.StudentResponse;
import com.jdbc.starter.mapper.StudentMapper;
import com.jdbc.starter.repository.StudentRepository;
import com.jdbc.starter.database.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<StudentResponse> getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(StudentMapper::toResponse);
    }

    public StudentResponse saveStudent(StudentRequest request) {
        Student student = StudentMapper.toEntity(request);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.toResponse(savedStudent);
    }

    public void updateStudent(Long id, StudentRequest request) {
        Student student = StudentMapper.toEntity(request);
        student.setId(id);
        studentRepository.update(student);
    }

    public boolean deleteStudent(Long id) {
        return studentRepository.delete(id);
    }

    public List<StudentResponse> getStudentsByGroup(Long groupId) {
        return studentRepository.findStudentsByGroup(groupId).stream()
                .map(StudentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> findStudentsByName(String name) {
        return studentRepository.findStudentsByName(name).stream()
                .map(StudentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<StudentResponse> getSortedStudents(String sort, String order) {
        return studentRepository.getSortedStudents(sort, order).stream()
                .map(StudentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
