package com.company.app.controller;

import java.io.IOException;

import com.company.app.dao.connection.DataSource;
import com.company.app.dao.impl.UserDaoImpl;
import com.company.app.service.UserService;
import com.company.app.service.dto.UserDto;
import com.company.app.service.impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private final UserService userService = new UserServiceImpl(new UserDaoImpl(new DataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        UserDto user = userService.getById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("jsp/user.jsp").forward(req, resp);
    }
}