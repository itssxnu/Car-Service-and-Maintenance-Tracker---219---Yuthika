package com.app.carmaintenance.car_maintenance.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vehicleName = request.getParameter("vehicleName");
        String numberPlate = request.getParameter("numberPlate");

        // Save to file (or storage logic)
        try (FileWriter writer = new FileWriter("D:/cars.txt", true)) {
            writer.write(vehicleName + " | " + numberPlate + "\n");
        }

        // ✅ Add the vehicle to a list or reload vehicles here
        // For now, we just simulate redirecting back to dashboard
        // You should ideally load the latest vehicle list here and set it:
        // request.setAttribute("vehicles", loadedVehiclesList);

        // Forward to dashboard.jsp instead of redirect
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}

