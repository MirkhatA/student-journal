package com.jdbc.starter.services;

import com.jdbc.starter.database.dto.request.GradeRequest;
import com.jdbc.starter.database.dto.response.GradeResponse;
import com.jdbc.starter.mapper.GradeMapper;
import com.jdbc.starter.repository.GradeRepository;
import com.jdbc.starter.database.entity.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<GradeResponse> getAllGrades() {
        return gradeRepository.findAll().stream()
                .map(GradeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<GradeResponse> getGradeById(Long id) {
        return gradeRepository.findById(id)
                .map(GradeMapper::toResponse);
    }

    public GradeResponse saveGrade(GradeRequest request) {
        Grade grade = GradeMapper.toEntity(request);
        Grade savedGrade = gradeRepository.save(grade);
        return GradeMapper.toResponse(savedGrade);
    }

    public void updateGrade(Long id, GradeRequest request) {
        Grade grade = GradeMapper.toEntity(request);
        grade.setId(id);
        gradeRepository.update(grade);
    }

    public boolean deleteGrade(Long id) {
        return gradeRepository.delete(id);
    }
}
