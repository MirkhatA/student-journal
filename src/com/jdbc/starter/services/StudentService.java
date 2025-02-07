package com.jdbc.starter.services;

import com.jdbc.starter.database.dao.StudentDao;
import com.jdbc.starter.database.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentDao studentDao;

    @Autowired
    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentDao.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentDao.save(student);
    }

    public void updateStudent(Student student) {
        studentDao.update(student);
    }

    public boolean deleteStudent(Long id) {
        return studentDao.delete(id);
    }

    public List<Student> getStudentsByGroup(Long groupId) {
        return studentDao.findStudentsByGroup(groupId);
    }

    public List<Student> findStudentsByName(String name) {
        return studentDao.findStudentsByName(name);
    }
}
