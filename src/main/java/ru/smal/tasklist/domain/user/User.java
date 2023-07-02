package ru.smal.tasklist.domain.user;

import lombok.Data;
import org.springframework.scheduling.config.Task;

import java.util.Set;

@Data
public class User {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String passwordConfirmation;
    private Set<Role> roles;
    private Set<Task> tasks;
}
