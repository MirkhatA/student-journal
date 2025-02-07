package com.jdbc.starter.database.dao;

import com.jdbc.starter.database.entity.Group;
import com.jdbc.starter.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GroupDao {

    private static final String DELETE_SQL = "DELETE FROM student_groups WHERE id = ?";
    private static final String SAVE_SQL = "INSERT INTO student_groups(name, created_at) VALUES (?, ?)";
    private static final String UPDATE_SQL = "UPDATE student_groups SET name = ? WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT id, name, created_at FROM student_groups";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";

    private final DataSource dataSource;

    @Autowired
    public GroupDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Group> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Group> groups = new ArrayList<>();
            while (resultSet.next()) {
                groups.add(buildGroup(resultSet));
            }
            return groups;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Group> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Group group = null;
            if (resultSet.next()) {
                group = buildGroup(resultSet);
            }
            return Optional.ofNullable(group);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(Group group) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.setLong(2, group.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Group save(Group group) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                group.setId(generatedKeys.getLong(1));
            }
            return group;
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

    private static Group buildGroup(ResultSet resultSet) throws SQLException {
        return new Group(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getTimestamp("created_at").toLocalDateTime()
        );
    }
}
