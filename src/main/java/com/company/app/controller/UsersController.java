package com.company.app.controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/users")
public class UsersController extends HttpServlet {
    private final UserService userService = new UserServiceImpl(new UserDaoImpl(new DataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UserDto> users = userService.getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("jsp/users.jsp").forward(req, resp);
    }
}
