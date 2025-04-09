package com.app.carmaintenance.car_maintenance.controller;

import com.app.carmaintenance.car_maintenance.model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {

    private static final String USER_FILE_PATH = "D:\\219-users\\users.txt";
    private static final String CAR_DATA_DIR = "D:\\car-data\\";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("UserModel.jsp");
            return;
        }

        UserModel user = (UserModel) session.getAttribute("user");
        String userEmail = user.getUserEmail().replaceAll("[^a-zA-Z0-9]", "_");
        String mail = user.getUserEmail();

        // Step 1: Delete the user's car file
        File carFile = new File(CAR_DATA_DIR + userEmail + ".txt");
        if (carFile.exists()) {
            carFile.delete();
        }

        // Step 2: Remove user from users.txt
        File inputFile = new File(USER_FILE_PATH);
        File tempFile = new File("D:\\219-users\\users_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.startsWith(mail + ",")) {
                    writer.println(currentLine);
                }
            }
        }

        // Replace original file
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }

        // Step 3: Invalidate session and redirect
        session.invalidate();
        response.sendRedirect("UserModel.jsp");
    }
}
