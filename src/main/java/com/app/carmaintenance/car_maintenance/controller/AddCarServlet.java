package com.app.carmaintenance.car_maintenance.controller;

import com.app.carmaintenance.car_maintenance.model.VehicleModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
    private static final String FILE_PATH = "D:/cars.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vehicleName = request.getParameter("vehicleName");
        String numberPlate = request.getParameter("numberPlate");

        // 1. Save to file
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(vehicleName + "|" + numberPlate);
        }

        // 2. Read all vehicles from file
        List<VehicleModel> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    VehicleModel vehicle = new VehicleModel();
                    vehicle.setVehicleNumber(parts[1].trim());
                    vehicle.setModel(parts[0].trim());
                    vehicles.add(vehicle);
                }
            }
        }

        // 3. Set to request and forward
        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
