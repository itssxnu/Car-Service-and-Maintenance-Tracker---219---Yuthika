package com.app.carmaintenance.car_maintenance.model;

public class VehicleModel {
    private String vehicleNumber;
    private String model;
    private ServiceHistory serviceHistory;

    public VehicleModel(String vehicleNumber, String model) {
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.serviceHistory = new ServiceHistory();
    }

    public VehicleModel() {}

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}

