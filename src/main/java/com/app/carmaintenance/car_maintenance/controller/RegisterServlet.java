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
        String role = request.getParameter("role");

        // Get current user from session
        HttpSession session = request.getSession(false);
        UserModel currentUser = (session != null) ? (UserModel) session.getAttribute("user") : null;

        // Only allow ADMIN role to be set if current user is admin AND they selected ADMIN role
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getRole())) {
            role = "USER"; // Force USER role for non-admin registrations
        } else {
            // For admin users, use the role they selected (default is USER from the dropdown)
            role = (role != null && !role.isEmpty()) ? role : "USER";
        }

        // Create user with fields in correct order
        UserModel newUser = new UserModel(email, password, username, phone, role);

        if (UserUtil.registerUser(newUser)) {
            if (currentUser != null && "ADMIN".equalsIgnoreCase(currentUser.getRole())) {
                response.sendRedirect("adminDashboard.jsp?success=User created successfully");
            } else {
                response.sendRedirect("UserModel.jsp?success=Registration successful");
            }
        } else {
            request.setAttribute("error", "User already exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}