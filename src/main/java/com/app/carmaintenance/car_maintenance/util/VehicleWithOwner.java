// VehicleWithOwner.java
package com.app.carmaintenance.car_maintenance.util;

import com.app.carmaintenance.car_maintenance.model.VehicleModel;

public class VehicleWithOwner {
    private VehicleModel vehicle;
    private String ownerEmail;

    public VehicleWithOwner(VehicleModel vehicle, String ownerEmail) {
        this.vehicle = vehicle;
        this.ownerEmail = ownerEmail;
    }

    // Getters
    public VehicleModel getVehicle() { return vehicle; }
    public String getOwnerEmail() { return ownerEmail; }
}