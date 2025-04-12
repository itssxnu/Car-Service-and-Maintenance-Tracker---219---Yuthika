package com.app.carmaintenance.car_maintenance.util;

import com.app.carmaintenance.car_maintenance.model.VehicleModel;
import com.app.carmaintenance.car_maintenance.model.ServiceRecord;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleUtil {
    private static final String VEHICLE_DATA_PATH = "D:/car-data/";

    // Simple Pair implementation
    public static class Pair<A, B> {
        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() { return first; }
        public B getSecond() { return second; }
    }

    // Admin: Delete ANY user's vehicle
    public static void deleteVehicle(String targetUserEmail, String vehicleNumber) throws IOException {
        String sanitizedEmail = sanitizeEmail(targetUserEmail);
        File userFile = new File(VEHICLE_DATA_PATH + sanitizedEmail + ".txt");

        if (!userFile.exists()) {
            throw new IOException("User file not found");
        }

        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (!(parts.length >= 3 && "VEHICLE".equals(parts[0]) && parts[2].trim().equals(vehicleNumber))) {
                    updatedLines.add(line);
                }
            }
        }

        writeLinesToFile(userFile, updatedLines);
    }

    // Admin: Delete a specific service record
    public static void deleteServiceRecord(String targetUserEmail, String vehicleNumber, String serviceDate)
            throws IOException {
        String sanitizedEmail = sanitizeEmail(targetUserEmail);
        File userFile = new File(VEHICLE_DATA_PATH + sanitizedEmail + ".txt");

        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (!(parts.length >= 10 && "SERVICE".equals(parts[0])
                        && parts[1].equals(vehicleNumber)
                        && parts[2].equals(serviceDate))) {
                    updatedLines.add(line);
                }
            }
        }

        writeLinesToFile(userFile, updatedLines);
    }

    // Get all vehicles with owner emails
    public static List<Pair<VehicleModel, String>> getAllVehiclesWithOwners() throws IOException {
        List<Pair<VehicleModel, String>> vehiclesWithOwners = new ArrayList<>();
        File dataDir = new File(VEHICLE_DATA_PATH);

        File[] userFiles = dataDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (userFiles == null) return vehiclesWithOwners;

        for (File userFile : userFiles) {
            String email = userFile.getName()
                    .replace(".txt", "")
                    .replace("_", "@");

            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3 && "VEHICLE".equals(parts[0])) {
                        VehicleModel vehicle = new VehicleModel(parts[2].trim(), parts[1].trim());
                        vehiclesWithOwners.add(new Pair<>(vehicle, email));
                    }
                }
            }
        }
        return vehiclesWithOwners;
    }

    // Add this method to VehicleUtil.java
    public static List<ServiceRecord> getServicesForVehicle(String userEmail, String vehicleNumber)
            throws IOException {
        List<ServiceRecord> services = new ArrayList<>();
        String sanitizedEmail = userEmail.replaceAll("[^a-zA-Z0-9]", "_");
        File userFile = new File(VEHICLE_DATA_PATH + sanitizedEmail + ".txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 10 && "SERVICE".equals(parts[0])
                        && parts[1].equals(vehicleNumber)) {
                    services.add(new ServiceRecord(
                            parts[2], parts[3], parts[4], parts[5],
                            parts[6], parts[7], parts[8], parts[9]
                    ));
                }
            }
        }
        return services;
    }

    // Helper methods
    private static String sanitizeEmail(String email) {
        return email.replaceAll("[^a-zA-Z0-9]", "_");
    }

    private static void writeLinesToFile(File file, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}