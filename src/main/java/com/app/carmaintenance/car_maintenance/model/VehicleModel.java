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

    public VehicleModel() {
        
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

    public void setvehicleNumber(String trim) {
    }

    public void setmodel(String trim) {
    }

    public void setmakeYear(String s) {
    }

    public void setVehicleNumber(String trim) {
    }

    public void setModel(String trim) {
    }

    public void setMakeYear(String s) {
    }
}

