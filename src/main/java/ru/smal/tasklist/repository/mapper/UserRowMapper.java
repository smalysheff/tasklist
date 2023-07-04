package ru.smal.tasklist.repository.mapper;

import ru.smal.tasklist.domain.task.Task;
import ru.smal.tasklist.domain.user.Role;
import ru.smal.tasklist.domain.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRowMapper {

    public static User mapRow(ResultSet resultSet) throws SQLException {
        Set<Role> roles = new HashSet<>();
        while (resultSet.next()) {
            roles.add(Role.valueOf(resultSet.getString("user_user_role")));
        }
        resultSet.beforeFirst();
        List<Task> tasks = TaskRowMapper.mapRows(resultSet);
        resultSet.beforeFirst();

        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setUsername(resultSet.getString("user_username"));
            user.setPassword(resultSet.getString("user_password"));
            user.setRoles(roles);
            user.setTasks(tasks);
            return user;
        }
        return null;
    }
}
