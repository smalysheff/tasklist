package ru.smal.tasklist.web.mapper;

import org.mapstruct.Mapper;
import ru.smal.tasklist.domain.task.Task;
import ru.smal.tasklist.web.dto.task.TaskDto;

@Mapper(componentModel = "spring")
public interface TaskMapper extends BaseMapper<Task, TaskDto>{
}
