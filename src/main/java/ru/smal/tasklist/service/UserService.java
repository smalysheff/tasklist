package ru.smal.tasklist.service;

import ru.smal.tasklist.domain.user.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String name);

    User update(User user);

    User create(User user);

    void delete(Long id);

    boolean isTaskOwner(Long userId, Long taskId);
}

