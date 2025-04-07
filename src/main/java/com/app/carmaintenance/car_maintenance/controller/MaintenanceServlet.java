package com.app.carmaintenance.car_maintenance.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/MaintenanceServlet")
public class MaintenanceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read input values
        String oilRenewDateStr = request.getParameter("oilRenewDate");
        String lastServiceDateStr = request.getParameter("lastServiceDate");
        String alignmentCheckDateStr = request.getParameter("alignmentCheckDate");
        String tyreRenewDateStr = request.getParameter("tyreRenewDate");
        String specialNotice = request.getParameter("specialNotice");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // Parse dates
            LocalDate oilRenewDate = LocalDate.parse(oilRenewDateStr, formatter);
            LocalDate lastServiceDate = LocalDate.parse(lastServiceDateStr, formatter);
            LocalDate alignmentCheckDate = LocalDate.parse(alignmentCheckDateStr, formatter);
            LocalDate tyreRenewDate = LocalDate.parse(tyreRenewDateStr, formatter);

            // Calculate next recommended dates
            LocalDate nextOilRefill = oilRenewDate.plusMonths(3);
            LocalDate nextServiceDate = lastServiceDate.plusMonths(6);
            LocalDate nextAlignmentCheck = alignmentCheckDate.plusMonths(6);
            LocalDate nextTyreRenewDate = tyreRenewDate.plusYears(1);

            // Set attributes for JSP
            request.setAttribute("bestOilRefillDate", nextOilRefill.format(formatter));
            request.setAttribute("nextServiceDate", nextServiceDate.format(formatter));
            request.setAttribute("nextAlignmentCheckDate", nextAlignmentCheck.format(formatter));
            request.setAttribute("nextTyreRenewDate", nextTyreRenewDate.format(formatter));
            request.setAttribute("specialNotice", specialNotice);

        } catch (Exception e) {
            request.setAttribute("specialNotice", "Invalid date format or data input.");
        }

        // Forward back to the JSP
        request.getRequestDispatcher("maintenace.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("maintenace.jsp");
    }
}
