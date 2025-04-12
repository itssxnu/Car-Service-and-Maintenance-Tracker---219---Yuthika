package com.app.carmaintenance.car_maintenance.controller;

import com.app.carmaintenance.car_maintenance.util.VehicleUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminDashboardServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized access");
            return;
        }

        String action = request.getParameter("action");
        String email = request.getParameter("targetUserEmail");
        String vehicleNumber = request.getParameter("vehicleNumber");

        try {
            if ("deleteVehicle".equals(action)) {
                VehicleUtil.deleteVehicle(email, vehicleNumber);
            } else if ("deleteService".equals(action)) {
                String serviceDate = request.getParameter("serviceDate");
                VehicleUtil.deleteServiceRecord(email, vehicleNumber, serviceDate);
            }
            response.sendRedirect("adminDashboard.jsp?success=true");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }
}
