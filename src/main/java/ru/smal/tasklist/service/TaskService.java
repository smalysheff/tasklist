package ru.smal.tasklist.service;

import ru.smal.tasklist.domain.task.Task;
import ru.smal.tasklist.domain.task.TaskImage;

import java.util.List;

public interface TaskService {
    
    Task getById(Long id);

    List<Task> getAllByUserId(Long userId);

    Task update(Task task);

    Task create(Task task, Long userId);

    void delete(Long id);

    void uploadImage(Long id, TaskImage image);
}
