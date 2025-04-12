package com.app.carmaintenance.car_maintenance.controller;

import com.app.carmaintenance.car_maintenance.model.UserModel;
import com.app.carmaintenance.car_maintenance.model.VehicleModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {

    private String getUserFilePath(String email) {
        return "D:/car-data/" + email.replaceAll("[^a-zA-Z0-9]", "_") + ".txt";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("UserModel.jsp");
            return;
        }

        File dir = new File("D:/car-data/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = getUserFilePath(user.getUserEmail());


        String vehicleName = request.getParameter("vehicleName");
        String numberPlate = request.getParameter("numberPlate");

        // 1. Save to file
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write( "VEHICLE|" + vehicleName + "|" + numberPlate + "\n"); // Add newline!
        }

        // 2. Redirect to dashboard so refresh won't repeat post
        response.sendRedirect("DashboardServlet");
    }

}
