package com.app.carmaintenance.car_maintenance.util;

import com.app.carmaintenance.car_maintenance.model.UserModel;
import java.io.*;

public class UserUtil {

    public static boolean registerUser(UserModel user) throws IOException {
        if (userExists(user.getUserEmail())) return false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Config.USERS_FILE, true))) {
            writer.write("USER|" + user.getUserEmail() + "|" + user.getPassword() + "|" + user.getUsername() + "|" + user.getUserMobileNumber());
            writer.newLine();
            return true;
        }
    }

    public static boolean userExists(String email) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(Config.USERS_FILE))) {
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

    public static UserModel authenticate(String email, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(Config.USERS_FILE))) {
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