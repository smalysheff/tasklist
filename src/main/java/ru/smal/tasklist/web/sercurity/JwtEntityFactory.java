package ru.smal.tasklist.web.sercurity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.smal.tasklist.domain.user.Role;
import ru.smal.tasklist.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class JwtEntityFactory {

    public static JwtEntity create(User user) {
        return new JwtEntity(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<SimpleGrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
