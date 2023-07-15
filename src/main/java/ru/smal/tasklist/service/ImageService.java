package ru.smal.tasklist.service;

import ru.smal.tasklist.domain.task.TaskImage;

public interface ImageService {

    String upload(TaskImage image);
}
