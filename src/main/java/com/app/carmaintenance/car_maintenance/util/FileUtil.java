package com.app.carmaintenance.car_maintenance.util;

import com.app.carmaintenance.car_maintenance.model.ServiceRecord;
import com.app.carmaintenance.car_maintenance.model.VehicleModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final String USER_FILE_PATH = "D:\\219-users\\users.txt";
    private static final String CAR_DATA_DIR = "D:\\car-data\\";

    public static List<ServiceRecord> readServiceRecords(File userFile, String vehicleNumber) throws IOException {
        List<ServiceRecord> records = new ArrayList<>();

        if (userFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 9 && "SERVICE".equals(parts[0]) && parts[1].equals(vehicleNumber)) {
                        records.add(new ServiceRecord(
                                parts[2],
                                parts[3],
                                parts[4],
                                parts.length > 5 ? parts[5] : "",
                                parts.length > 6 ? parts[6] : "",
                                parts.length > 7 ? parts[7] : "",
                                parts.length > 8 ? parts[8] : "",
                                parts.length > 9 ? parts[9] : ""
                        ));
                    }
                }
            }
        }

        return records;
    }

    public static List<VehicleModel> readVehiclesFromFile(File userFile) {
        List<VehicleModel> vehicles = new ArrayList<>();

        if (userFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3 && "VEHICLE".equals(parts[0])) {
                        VehicleModel vehicle = new VehicleModel(parts[2].trim(), parts[1].trim());
                        vehicles.add(vehicle);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return vehicles;
    }

    public static void deleteVehicleFromUserFile(String email, String vehicleNumber) throws IOException {
        String sanitizedEmail = email.replaceAll("[^a-zA-Z0-9]", "_");
        File userFile = new File(CAR_DATA_DIR + sanitizedEmail + ".txt");

        if (!userFile.exists()) return;

        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!(line.startsWith("VEHICLE") && line.contains(vehicleNumber))) {
                    updatedLines.add(line);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        }
    }

    public static void deleteServiceFromUserFile(String email, String vehicleNumber, String serviceDate) throws IOException {
        String sanitizedEmail = email.replaceAll("[^a-zA-Z0-9]", "_");
        File userFile = new File(CAR_DATA_DIR + sanitizedEmail + ".txt");

        if (!userFile.exists()) return;

        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (!(parts.length >= 10 &&
                        "SERVICE".equals(parts[0]) &&
                        parts[1].equals(vehicleNumber) &&
                        parts[2].equals(serviceDate))) {
                    updatedLines.add(line);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        }
    }

    public static void deleteUserAccount(String email) throws IOException {
        String sanitizedEmail = email.replaceAll("[^a-zA-Z0-9]", "_");

        File carFile = new File(CAR_DATA_DIR + sanitizedEmail + ".txt");
        if (carFile.exists()) {
            carFile.delete();
        }

        File inputFile = new File(USER_FILE_PATH);
        File tempFile = new File("D:\\219-users\\users_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.startsWith(email + ",")) {
                    writer.println(currentLine);
                }
            }
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }

    public static void appendServiceRecord(String email, String vehicleNumber, ServiceRecord record) throws IOException {
        String sanitizedEmail = email.replaceAll("[^a-zA-Z0-9]", "_");
        File userFile = new File(CAR_DATA_DIR + sanitizedEmail + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true))) {
            writer.write("SERVICE|" + vehicleNumber + "|" +
                    record.getDate() + "|" +
                    record.getServiceType() + "|" +
                    record.getMechanic() + "|" +
                    record.getOilRenewDate() + "|" +
                    record.getLastServiceDate() + "|" +
                    record.getAlignmentCheckDate() + "|" +
                    record.getTyreRenewDate() + "|" +
                    record.getSpecialNotice());
            writer.newLine();
        }
    }

    public static void appendVehicle(String email, VehicleModel vehicle) throws IOException {
        String sanitizedEmail = email.replaceAll("[^a-zA-Z0-9]", "_");
        File dir = new File(CAR_DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File userFile = new File(CAR_DATA_DIR + sanitizedEmail + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true))) {
            writer.write("VEHICLE|" + vehicle.getModel() + "|" + vehicle.getVehicleNumber());
            writer.newLine();
        }
    }
}
