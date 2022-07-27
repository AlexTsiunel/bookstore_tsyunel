package com.company.app.controller;

import jakarta.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/error.jsp";
    }
}
