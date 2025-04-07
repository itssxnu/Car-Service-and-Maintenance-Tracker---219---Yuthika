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
            writer.write(vehicleName + "|" + numberPlate + "\n"); // Add newline!
        }

        // 2. Redirect to dashboard so refresh won't repeat post
        response.sendRedirect("DashboardServlet");
    }

}
