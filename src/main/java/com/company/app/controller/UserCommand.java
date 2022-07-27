package com.company.app.controller;

import com.company.app.service.UserService;
import com.company.app.service.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;

public class UserCommand implements Command {
    private final UserService userService;

    public UserCommand(UserService userService) {
        super();
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req){
        Long id = Long.parseLong(req.getParameter("id"));
        UserDto user = userService.getById(id);
        req.setAttribute("user", user);
        return "jsp/user.jsp";
    }
}