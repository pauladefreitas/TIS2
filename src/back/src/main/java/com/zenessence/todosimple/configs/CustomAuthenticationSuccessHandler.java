package com.zenessence.todosimple.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.zenessence.todosimple.models.User;
import com.zenessence.todosimple.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("admin")) {
            response.sendRedirect("/agendamentoGerencia");
        } else {
            User user = userService.findByUsername(authentication.getName());
            if (userService.isAdmin(user)) {
                response.sendRedirect("/agendamentoGerencia");
            } else {
                response.sendRedirect("/agendamentoPage");
            }
        }
    }
}

