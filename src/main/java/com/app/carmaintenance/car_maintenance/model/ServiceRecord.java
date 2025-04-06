package com.app.carmaintenance.car_maintenance.model;

public class ServiceRecord {
    private String date; //yyyy-MM-dd
    private String serviceType;
    private String mechanic;

    public ServiceRecord() {
    }

    public ServiceRecord(String date, String serviceType, String mechanic) {
        this.date = date;
        this.serviceType = serviceType;
        this.mechanic = mechanic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }
}
