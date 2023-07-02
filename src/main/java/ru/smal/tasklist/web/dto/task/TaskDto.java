package ru.smal.tasklist.web.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import ru.smal.tasklist.domain.task.Status;
import ru.smal.tasklist.web.dto.validation.OnCreate;
import ru.smal.tasklist.web.dto.validation.OnUpdate;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    @NotNull(message = "Id cannot be empty", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Title cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Title cannot be longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Length(max = 255, message = "Description cannot be longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String description;


    private Status status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;
}
