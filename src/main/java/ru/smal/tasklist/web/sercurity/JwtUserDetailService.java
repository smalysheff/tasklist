package ru.smal.tasklist.web.sercurity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.smal.tasklist.domain.user.User;
import ru.smal.tasklist.service.UserService;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        return JwtEntityFactory.create(user);

    }
}
