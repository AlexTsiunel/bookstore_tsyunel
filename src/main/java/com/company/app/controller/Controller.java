package com.company.app.controller;

import java.io.IOException;

import com.company.app.dao.connection.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controller")
//@WebServlet("/")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        Command commandInstance = CommandFactory.getInstance().getCommand(command);
        String path = commandInstance.execute(req);
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    public void destroy() {
        DataSource.getInstance().close();
        super.destroy();
    }
    
    
}
