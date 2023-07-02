package ru.smal.tasklist.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.scheduling.config.Task;
import ru.smal.tasklist.web.dto.task.TaskDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toDto(Task task);

    List<TaskDto> toDto(List<Task> tasks);

    Task toEntity(TaskDto taskDto);
}
