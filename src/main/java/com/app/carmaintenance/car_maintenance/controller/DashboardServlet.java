package com.app.carmaintenance.car_maintenance.controller;

import com.app.carmaintenance.car_maintenance.model.UserModel;
import com.app.carmaintenance.car_maintenance.model.VehicleModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final String BASE_PATH = "D:/car-data/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("UserModel.jsp");
            return;
        }

        // Create directory if it doesn't exist
        File dir = new File(BASE_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // File for current user (sanitize email for filename)
        String sanitizedEmail = user.getUserEmail().replaceAll("[^a-zA-Z0-9]", "_");
        File userFile = new File(BASE_PATH + sanitizedEmail + ".txt");

        List<VehicleModel> vehicles = new ArrayList<>();

        // Read vehicle data for that user
        if (userFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 2) {
                        VehicleModel vehicle = new VehicleModel(parts[1].trim(), parts[0].trim());
                        vehicles.add(vehicle);
                    }
                }
            }
        }

        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
