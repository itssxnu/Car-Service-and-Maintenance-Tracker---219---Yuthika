package com.app.carmaintenance.car_maintenance.controller;

import com.app.carmaintenance.car_maintenance.model.UserModel;
import com.app.carmaintenance.car_maintenance.util.FileUtil;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        UserModel user = (UserModel) session.getAttribute("user");

        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized access");
            return;
        }

        String action = request.getParameter("action");
        String email = request.getParameter("targetUserEmail");
        String vehicleNumber = request.getParameter("vehicleNumber");

        try {
            switch (action) {
                case "deleteVehicle":
                    FileUtil.deleteVehicleFromUserFile(email, vehicleNumber);
                    break;
                case "deleteService":
                    String serviceDate = request.getParameter("serviceDate");
                    FileUtil.deleteServiceFromUserFile(email, vehicleNumber, serviceDate);
                    break;
                // Add other cases if needed
            }

            response.sendRedirect("adminDashboard.jsp?success=true");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }
}
