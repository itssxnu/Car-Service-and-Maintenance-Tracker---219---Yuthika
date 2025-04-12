package com.app.carmaintenance.car_maintenance.util;

import com.app.carmaintenance.car_maintenance.model.UserModel;

import java.io.*;
import java.util.*;

public class UserUtil {
    private static final String FILE_PATH =
            "D:\\219-users\\users.txt";

    // Save user to file
    public static boolean registerUser(UserModel user) throws IOException {
        if (userExists(user.getUserEmail())) return false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write( "USER|" + user.getUserEmail() + "|" + user.getPassword() + "|" + user.getUsername() + "|" + user.getUserMobileNumber());
            writer.newLine();
            return true;
        }
    }

    // Check if user exists by email
    public static boolean userExists(String email) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2 && parts[1].equalsIgnoreCase(email)) {
                    return true;
                }
            }
        }
        return false;
    }


    // Authenticate user by email and password
    public static UserModel authenticate(String email, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5 &&
                        parts[1].equalsIgnoreCase(email) &&
                        parts[2].equals(password)) {

                    UserModel user = new UserModel();
                    user.setRole(parts[0]);
                    user.setUserEmail(parts[1]);
                    user.setPassword(parts[2]);
                    user.setUsername(parts[3]);
                    user.setUserMobileNumber(parts[4]);
                    return user;
                }
            }
        }
        return null;
    }

}
