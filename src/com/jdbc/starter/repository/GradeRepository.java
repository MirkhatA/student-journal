package com.jdbc.starter.repository;

import com.jdbc.starter.database.entity.Grade;
import com.jdbc.starter.exception.DaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GradeRepository {

    private static final String DELETE_SQL = "DELETE FROM grades WHERE id = ?";
    private static final String SAVE_SQL = "INSERT INTO grades(student_id, subject, score, created_at) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE grades SET student_id = ?, subject = ?, score = ? WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT id, student_id, subject, score, created_at FROM grades";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";
    private static final String FIND_BY_STUDENT_ID_SQL = FIND_ALL_SQL + " WHERE student_id = ?";

    private final DataSource dataSource;

    public List<Grade> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Grade> grades = new ArrayList<>();
            while (resultSet.next()) {
                grades.add(buildGrade(resultSet));
            }
            return grades;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Grade> getAllGradesByStudentId(int studentId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_STUDENT_ID_SQL)) {
            preparedStatement.setLong(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Grade> grades = new ArrayList<>();
            while (resultSet.next()) {
                grades.add(buildGrade(resultSet));
            }
            return grades;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Grade> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Grade grade = null;
            if (resultSet.next()) {
                grade = buildGrade(resultSet);
            }
            return Optional.ofNullable(grade);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Grade save(Grade grade) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, grade.getStudentId());
            preparedStatement.setString(2, grade.getSubject());
            preparedStatement.setDouble(3, grade.getScore());
            preparedStatement.setDate(4, Date.valueOf(grade.getCreatedAt()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                grade.setId(generatedKeys.getLong(1));
            }
            return grade;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(Grade grade) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, grade.getStudentId());
            preparedStatement.setString(2, grade.getSubject());
            preparedStatement.setDouble(3, grade.getScore());
            preparedStatement.setLong(4, grade.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static Grade buildGrade(ResultSet resultSet) throws SQLException {
        return new Grade(
                resultSet.getLong("id"),
                resultSet.getLong("student_id"),
                resultSet.getString("subject"),
                resultSet.getDouble("score"),
                resultSet.getDate("created_at").toLocalDate()
        );
    }

}
