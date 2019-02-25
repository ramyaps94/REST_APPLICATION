package com.parkinglot.MultiLevelParkingLot.service;

import java.util.List;
import com.parkinglot.MultiLevelParkingLot.entity.Vehicle;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(long vehicleId);
    Vehicle getVehicleByLicenseNumber(String licenseNumber);
    boolean addVehicle(Vehicle vehicle);
    void updateVehicle(Vehicle vehicle);
    void deleteVehicle(Vehicle vehicle);
    boolean parkVehicle(Vehicle vehicle);
    void unParkVehicle(Vehicle vehicle);
}
