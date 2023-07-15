package ru.smal.tasklist.web.mapper;

import org.mapstruct.Mapper;
import ru.smal.tasklist.domain.user.User;
import ru.smal.tasklist.web.dto.user.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto>{
}
