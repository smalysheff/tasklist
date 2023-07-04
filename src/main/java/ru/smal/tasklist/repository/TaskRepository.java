package ru.smal.tasklist.repository;

import org.springframework.stereotype.Repository;
import ru.smal.tasklist.domain.task.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository {

    Optional<Task> findById(Long id);

    List<Task> findAllByUserId(Long userId);

    void assignToUserById(Long taskId, Long userId);

    void update(Task task);

    void create(Task task);

    void delete(Long id);
}
