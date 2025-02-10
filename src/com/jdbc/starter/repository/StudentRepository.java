package com.jdbc.starter.repository;

import com.jdbc.starter.database.entity.Student;
import com.jdbc.starter.exception.DaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private static final String DELETE_SQL = "DELETE FROM students WHERE id = ?";
    private static final String SAVE_SQL = "INSERT INTO students(first_name, last_name, group_id, created_at) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE students SET first_name = ?, last_name = ?, group_id = ? WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT id, first_name, last_name, group_id, created_at FROM students";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";
    private static final String FIND_BY_NAME_SQL = FIND_ALL_SQL + " WHERE first_name LIKE ? OR last_name LIKE ? ORDER BY first_name ASC, last_name ASC";

    private static final String FIND_STUDENTS_BY_GROUP_SQL = "SELECT id, first_name, last_name, group_id, created_at FROM students WHERE group_id = ?";
    private static final String FIND_STUDENTS_BY_NAME_SQL = "SELECT id, first_name, last_name, group_id, created_at FROM students WHERE first_name LIKE ? OR last_name LIKE ? ORDER BY first_name ASC, last_name ASC";
    private static final String ORDER_STUDENT_BY_NAME = FIND_ALL_SQL + " ORDER BY %s %s";

    private final DataSource dataSource;

    public List<Student> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Student> findByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_SQL)) {
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Student> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Student student = null;
            if (resultSet.next()) {
                student = buildStudent(resultSet);
            }
            return Optional.ofNullable(student);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(Student student) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setLong(3, student.getGroupId());
            preparedStatement.setLong(4, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Student save(Student student) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setLong(3, student.getGroupId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getLong(1));
            }
            return student;
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

    public List<Student> findStudentsByGroup(Long groupId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_STUDENTS_BY_GROUP_SQL)) {
            preparedStatement.setLong(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Student> findStudentsByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_STUDENTS_BY_NAME_SQL)) {
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Student> getSortedStudents(String sort, String order) {
        String column = switch (sort) {
            case "lastName" -> "last_name";
            default -> "first_name";
        };
        String direction = order.equalsIgnoreCase("desc") ? "DESC" : "ASC";
        String sql = String.format(ORDER_STUDENT_BY_NAME, column, direction);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static Student buildStudent(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getLong("group_id"),
                resultSet.getTimestamp("created_at").toLocalDateTime()
        );
    }
}
