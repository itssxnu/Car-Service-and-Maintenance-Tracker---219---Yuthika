package com.app.carmaintenance.car_maintenance.model;

public class VehicleModel {
    private String vehicleNumber;
    private String model;
    private int makeYear;
    private ServiceHistory serviceHistory;

    public VehicleModel(String vehicleNumber, String model, int makeYear) {
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.makeYear = makeYear;
        this.serviceHistory = new ServiceHistory();
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getModel() {
        return model;
    }

    public int getMakeYear() {
        return makeYear;
    }

    public ServiceHistory getServiceHistory() {
        return serviceHistory;
    }
}

