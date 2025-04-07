package com.app.carmaintenance.car_maintenance.controller;

import com.app.carmaintenance.car_maintenance.model.VehicleModel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final String FILE_PATH = "D:/cars.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<VehicleModel> vehicles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    VehicleModel vehicle = new VehicleModel();
                    vehicle.setModel(parts[0].trim());
                    vehicle.setVehicleNumber(parts[1].trim());
                    vehicles.add(vehicle);
                }
            }
        }

        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
