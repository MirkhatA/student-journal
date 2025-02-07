package com.jdbc.starter.services;

import com.jdbc.starter.database.dao.GradeDao;
import com.jdbc.starter.database.entity.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    private final GradeDao gradeDao;

    @Autowired
    public GradeService(GradeDao gradeDao) {
        this.gradeDao = gradeDao;
    }

    public List<Grade> getAllGrades() {
        return gradeDao.findAll();
    }

    public Optional<Grade> getGradeById(Long id) {
        return gradeDao.findById(id);
    }

    public Grade saveGrade(Grade grade) {
        return gradeDao.save(grade);
    }

    public void updateGrade(Grade grade) {
        gradeDao.update(grade);
    }

    public boolean deleteGrade(Long id) {
        return gradeDao.delete(id);
    }
}
