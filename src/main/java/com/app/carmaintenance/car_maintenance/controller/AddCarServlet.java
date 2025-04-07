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

        // Save to file (append mode)
        try (FileWriter writer = new FileWriter("D:/cars.txt", true)) { // Change path as needed
            writer.write(vehicleName + " | " + numberPlate + "\n");
        }

        // Confirmation HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Car Added</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("</head><body class='bg-light'>");
        out.println("<div class='container mt-5 text-center'>");
        out.println("<div class='alert alert-success'>");
        out.println("<h4 class='alert-heading'>Car Added Successfully!</h4>");
        out.println("<p>Vehicle: <strong>" + vehicleName + "</strong><br>Number Plate: <strong>" + numberPlate + "</strong></p>");
        out.println("<hr>");
        out.println("<a href='addCar.jsp' class='btn btn-primary'>Add Another Car</a>");
        out.println("</div></div>");
        out.println("</body></html>");
    }
}
