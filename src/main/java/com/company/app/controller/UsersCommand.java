package com.company.app.controller;

import java.util.List;

import com.company.app.service.UserService;
import com.company.app.service.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;

public class UsersCommand implements Command {
    
    private final  UserService userService;

    public UsersCommand(UserService userService) {
        super();
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<UserDto> users = userService.getAll();
        req.setAttribute("users", users);
        return "jsp/users.jsp";
    }
}
