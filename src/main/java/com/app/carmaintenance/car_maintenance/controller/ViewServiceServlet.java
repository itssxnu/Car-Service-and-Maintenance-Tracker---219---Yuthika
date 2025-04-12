package com.app.carmaintenance.car_maintenance.controller;

import com.app.carmaintenance.car_maintenance.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/ViewServiceServlet")
public class ViewServiceServlet extends HttpServlet {

    private static final String DATA_PATH = "D:\\car-data\\";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("UserModel.jsp");
            return;
        }

        String vehicleNumber = request.getParameter("vehicleNumber");
        if (vehicleNumber == null || vehicleNumber.isEmpty()) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        // Get user email and build their specific file path
        UserModel user = (UserModel) session.getAttribute("user");
        String userEmail = user.getUserEmail().replaceAll("[^a-zA-Z0-9]", "_");
        File userFile = new File(DATA_PATH + userEmail + ".txt");

        ServiceHistory history = new ServiceHistory();

        if (userFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 9 && "SERVICE".equals(parts[0]) && parts[1].equals(vehicleNumber)) {
                        ServiceRecord record = new ServiceRecord(
                                parts[2],                             // date
                                parts[3],                             // serviceType
                                parts[4],                             // mechanic
                                parts.length > 5 ? parts[5] : "",     // oilRenewDate
                                parts.length > 6 ? parts[6] : "",     // lastServiceDate
                                parts.length > 7 ? parts[7] : "",     // alignmentCheckDate
                                parts.length > 8 ? parts[8] : "",     // tyreRenewDate
                                parts.length > 9 ? parts[9] : ""      // specialNotice
                        );


                        history.add(record);
                    }
                }
            }
        }

        history.selectionSortByDate();
        request.setAttribute("history", history.getAll());
        request.setAttribute("vehicleNumber", vehicleNumber);
        request.getRequestDispatcher("viewHistory.jsp").forward(request, response);
    }
}
