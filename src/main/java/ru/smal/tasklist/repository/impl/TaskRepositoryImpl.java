package ru.smal.tasklist.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.smal.tasklist.domain.exception.ResourceNotFoundException;
import ru.smal.tasklist.domain.task.Task;
import ru.smal.tasklist.repository.DataSourceConfig;
import ru.smal.tasklist.repository.TaskRepository;
import ru.smal.tasklist.repository.mapper.TaskRowMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;

//@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final DataSourceConfig dataSourceConfig;

    private final String FIND_BY_ID = """
            SELECT t.id              as task_id,
                   t.title           as task_title,
                   t.description     as task_description,
                   t.expiration_date as task_expiration_date,
                   t.status          as task_status
            FROM tasks t
            WHERE id = ?
            """;

    private final String FIND_ALL_BY_USER_ID = """
            SELECT t.id              as task_id,
                   t.title           as title,
                   t.description     as description,
                   t.expiration_date as expiration_date,
                   t.status          as status
            FROM tasks t
            JOIN users_tasks ut on t.id = ut.task_id
            WHERE ut.user_id = ?
            """;

    private final String ASSIGN = """
            INSERT INTO users_tasks(task_id, user_id)
            VALUES (?, ?)
            """;

    private final String UPDATE = """
            UPDATE tasks
            SET title = ?,
                description = ?,
                expiration_date = ?,
                status = ?
            WHERE id = ?
            """;

    private final String CREATE = """
            INSERT INTO tasks(title, description, expiration_date, status) 
            VALUES (?, ?, ?, ?)
            """;

    private final String DELETE = """
            DELETE FROM tasks
            WHERE id = ?
            """;


    @Override
    public Optional<Task> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return Optional.ofNullable(TaskRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while finding Task by ID");
        }
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {

                return TaskRowMapper.mapRows(resultSet);
            }
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while finding all by ID");
        }
    }

    @Override
    public void assignToUserById(Long taskId, Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(ASSIGN);
            statement.setLong(1, userId);
            statement.setLong(2, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while finding all by ID");
        }
    }

    @Override
    public void update(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, task.getTitle());
            if (task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }
            if (task.getExpirationDate() == null) {
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            }
            statement.setString(4, task.getStatus().name());
            statement.setLong(5, task.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while updating Task");
        }
    }

    @Override
    public void create(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getTitle());
            if (task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }
            if (task.getExpirationDate() == null) {
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            }
            statement.setString(4, task.getStatus().name());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                task.setId(keys.getLong(1));
            } else {
                throw new SQLException("Creating Task failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while creating Task");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while deleting Task");
        }
    }
}
