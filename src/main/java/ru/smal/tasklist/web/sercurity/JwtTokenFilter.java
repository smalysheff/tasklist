package ru.smal.tasklist.web.sercurity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.smal.tasklist.domain.exception.ResourceNotFoundException;

import java.io.IOException;

@AllArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String barerToken = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        if (barerToken != null && barerToken.startsWith("Bearer ")) {
            barerToken = barerToken.substring(7);
        }
        if (barerToken != null && jwtTokenProvider.validateToken(barerToken)) {
            try {
                Authentication authentication = jwtTokenProvider.getAuthentication(barerToken);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ResourceNotFoundException ignored) {
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
