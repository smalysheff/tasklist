package ru.smal.tasklist.web.mapper;

import org.mapstruct.Mapper;
import ru.smal.tasklist.domain.task.TaskImage;
import ru.smal.tasklist.web.dto.task.TaskImageDto;

@Mapper(componentModel = "spring")
public interface TaskImageMapper extends BaseMapper<TaskImage, TaskImageDto> {
}
