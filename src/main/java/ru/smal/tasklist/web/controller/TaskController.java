package ru.smal.tasklist.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ru.smal.tasklist.domain.task.Task;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.smal.tasklist.service.TaskService;
import ru.smal.tasklist.web.dto.task.TaskDto;
import ru.smal.tasklist.web.dto.validation.OnUpdate;
import ru.smal.tasklist.web.mapper.TaskMapper;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
@Validated
@Tag(name = "Task Controller", description = "Task controller")
public class TaskController {

    private final TaskMapper taskMapper;
    private final TaskService taskService;

    @PutMapping
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        taskService.update(task);
        return taskMapper.toDto(task);
    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        taskService.delete(id);
    }
}
