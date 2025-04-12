package com.app.carmaintenance.car_maintenance.controller;

import com.app.carmaintenance.car_maintenance.model.ServiceRecord;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/AddServiceServlet")
public class AddServiceServlet extends HttpServlet {

    private static final String CAR_DATA_DIR = "D:/car-data/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("UserModel.jsp");
            return;
        }

        String vehicleNumber = request.getParameter("vehicleNumber");
        String date = request.getParameter("date");
        String serviceType = request.getParameter("serviceType");
        String mechanic = request.getParameter("mechanic");
        String oilRenewDate = request.getParameter("oilRenewDate");
        String lastServiceDate = request.getParameter("lastServiceDate");
        String alignmentCheckDate = request.getParameter("alignmentCheckDate");
        String tyreRenewDate = request.getParameter("tyreRenewDate");
        String specialNotice = request.getParameter("specialNotice");

        ServiceRecord newService = new ServiceRecord(date, serviceType, mechanic, oilRenewDate, lastServiceDate, alignmentCheckDate, tyreRenewDate, specialNotice);

        String userEmail = ((com.app.carmaintenance.car_maintenance.model.UserModel) session.getAttribute("user"))
                .getUserEmail().replaceAll("[^a-zA-Z0-9]", "_");

        File userFile = new File(CAR_DATA_DIR + userEmail + ".txt");

        // Append service record line for this vehicle
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true))) {
            writer.write("SERVICE|" + vehicleNumber + "|" + date + "|" + serviceType + "|" + mechanic + "|" +
                    oilRenewDate + "|" + lastServiceDate + "|" + alignmentCheckDate + "|" +
                    tyreRenewDate + "|" + specialNotice);
            writer.newLine();
        }


        // Redirect back to dashboard
        response.sendRedirect("DashboardServlet");
    }
}
