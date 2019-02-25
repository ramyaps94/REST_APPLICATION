package com.parkinglot.MultiLevelParkingLot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parkinglot.MultiLevelParkingLot.entity.Vehicle;
import com.parkinglot.MultiLevelParkingLot.service.VehicleService;
import com.parkinglot.MultiLevelParkingLot.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService{
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> list = new ArrayList<>();
        vehicleRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Vehicle getVehicleById(long vehicleId) {
        Vehicle obj = vehicleRepository.findByVehicleId(vehicleId);
        return obj;
    }

    @Override
    public Vehicle getVehicleByLicenseNumber(String licenseNumber) {
        Vehicle obj = vehicleRepository.findByVehicleLicenseNumber(licenseNumber);
        return obj;
    }

    @Override
    public synchronized boolean addVehicle(Vehicle vehicle) {
        Vehicle foundVehicle = vehicleRepository.findByVehicleLicenseNumber(vehicle.getVehicleLicenseNumber());
        if (foundVehicle!=null) {
            return false;
        } else {
            vehicle = vehicleRepository.save(vehicle);
            return true;
        }
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {
        vehicleRepository.delete(vehicle);
    }

    @Override
    public boolean parkVehicle(Vehicle vehicle) {
        boolean status = vehicleRepository.parkVehicle(vehicle);
        return status;
    }

    @Override
    public void unParkVehicle(Vehicle vehicle) {
        vehicleRepository.unParkVehicle(vehicle);
    }
}
