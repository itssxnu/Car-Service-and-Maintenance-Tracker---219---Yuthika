package com.app.carmaintenance.car_maintenance.controller;


import com.app.carmaintenance.car_maintenance.model.UserModel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    // In a real application, you would use a service/DAO to check credentials
    // This is just a mock implementation for demonstration
    private boolean authenticateUser(String email, String password) {
        // Mock validation - replace with actual database check
        return "admin@example.com".equals(email) && "admin123".equals(password);
    }

    // Mock method to get user details - replace with actual database access
    private UserModel getUserByEmail(String email) {
        if ("admin@example.com".equals(email)) {
            return new UserModel("admin@example.com", "Admin User", "1234567890");
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("UserEmail");
        String password = request.getParameter("password");

        // Validate input
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Email and password are required");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Authenticate user
        if (authenticateUser(email, password)) {
            // Get user details
            UserModel user = getUserByEmail(email);

            if (user != null) {
                // Create session and store user details
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // Set session timeout (30 minutes)
                session.setMaxInactiveInterval(30 * 60);

                // Redirect to home page or dashboard
                response.sendRedirect("dashboard.jsp");
                return;
            }
        }

        // Authentication failed
        request.setAttribute("error", "Invalid email or password");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // If someone tries to access login via GET, just show the login page
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}