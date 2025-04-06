package com.app.carmaintenance.car_maintenance.controller;

import com.app.carmaintenance.car_maintenance.model.UserModel;
import com.app.carmaintenance.car_maintenance.util.UserUtil;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("UserEmail");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");

        UserModel newUser = new UserModel(email, password, username, phone);

        if (UserUtil.registerUser(newUser)) {
            response.sendRedirect("UserModel.jsp");
        } else {
            request.setAttribute("error", "User already exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
