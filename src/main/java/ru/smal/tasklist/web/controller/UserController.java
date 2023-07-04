package ru.smal.tasklist.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.smal.tasklist.domain.task.Task;
import ru.smal.tasklist.domain.user.User;
import ru.smal.tasklist.service.TaskService;
import ru.smal.tasklist.service.UserService;
import ru.smal.tasklist.web.dto.task.TaskDto;
import ru.smal.tasklist.web.dto.user.UserDto;
import ru.smal.tasklist.web.dto.validation.OnCreate;
import ru.smal.tasklist.web.dto.validation.OnUpdate;
import ru.smal.tasklist.web.mapper.TaskMapper;
import ru.smal.tasklist.web.mapper.UserMapper;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @PostMapping
    public UserDto create(@Validated(OnCreate.class) @RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        User created = userService.create(user);
        return userMapper.toDto(created);
    }

    @PutMapping
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        User updated = userService.update(user);
        return userMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getUserTasks(@PathVariable Long id) {
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    public TaskDto createTask(@PathVariable Long id, @Validated(OnCreate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }

}
