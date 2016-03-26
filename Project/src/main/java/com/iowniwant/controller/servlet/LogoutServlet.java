package com.iowniwant.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sulfur on 25.03.16.
 */

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logoutServlet"})
public class LogoutServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().invalidate();
        response.sendRedirect("login.jsp");
    }
}
