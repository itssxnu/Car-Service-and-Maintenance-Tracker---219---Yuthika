package com.app.carmaintenance.car_maintenance.dao;

import com.app.carmaintenance.car_maintenance.model.CustomerModel;
import com.app.carmaintenance.car_maintenance.util.Config;
import com.app.carmaintenance.car_maintenance.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // Get all customers by scanning the directory
    public List<CustomerModel> getAllCustomers() {
        List<CustomerModel> customers = new ArrayList<>();
        File folder = new File(Config.CUSTOMER_FILE);

        if (!folder.exists()) return customers;

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null) return customers;

        for (File file : files) {
            try {
                String[] parts = FileUtil.readCustomerDetailsFromFile(file);
                if (parts != null && parts.length >= 4) {
                    customers.add(new CustomerModel(parts[1], parts[2], parts[3]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return customers;
    }

    public CustomerModel getCustomerByEmail(String email) {
        try {
            String[] parts = FileUtil.readCustomerDetails(email);  // uses email to construct the file path internally
            if (parts != null && parts.length >= 4) {
                return new CustomerModel(parts[1], parts[2], parts[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void saveOrUpdateCustomer(CustomerModel customer) throws IOException {
        FileUtil.writeCustomerDetails(customer.getName(), customer.getEmail(), customer.getPhoneNumber());
    }
}
