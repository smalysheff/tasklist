package ru.smal.tasklist.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.smal.tasklist.domain.task.Task;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.smal.tasklist.domain.task.TaskImage;
import ru.smal.tasklist.service.TaskService;
import ru.smal.tasklist.web.dto.task.TaskDto;
import ru.smal.tasklist.web.dto.task.TaskImageDto;
import ru.smal.tasklist.web.dto.validation.OnUpdate;
import ru.smal.tasklist.web.mapper.TaskImageMapper;
import ru.smal.tasklist.web.mapper.TaskMapper;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
@Validated
@Tag(name = "Task Controller", description = "Task controller")
public class TaskController {

    private final TaskMapper taskMapper;
    private final TaskService taskService;
    private final TaskImageMapper taskImageMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get TaskDto by id")
    @PreAuthorize("canAccessTask(#id)")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @PutMapping
    @Operation(summary = "Update TaskDto by id")
    @PreAuthorize("canAccessTask(#dto.id)")
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        taskService.update(task);
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete TaskDto by id")
    @PreAuthorize("canAccessTask(#id)")
    public void deleteById(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PostMapping("/{id}/image")
    @Operation(summary = "Upload image to task")
    @PreAuthorize("canAccessTask(#id)")
    public void uploadImage(@PathVariable Long id,
                            @Validated @ModelAttribute TaskImageDto imageDto) {
        TaskImage image = taskImageMapper.toEntity(imageDto);
        taskService.uploadImage(id, image);
    }

}
