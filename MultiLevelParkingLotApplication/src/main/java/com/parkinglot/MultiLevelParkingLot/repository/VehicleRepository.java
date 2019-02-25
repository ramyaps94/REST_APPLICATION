package com.parkinglot.MultiLevelParkingLot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.parkinglot.MultiLevelParkingLot.entity.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    Vehicle findByVehicleId(long vehicleId);
    Vehicle findByVehicleLicenseNumber(String licenseNumber);


    default boolean parkVehicle(Vehicle vehicle) {
        return vehicle.park();
    }

    default void unParkVehicle(Vehicle vehicle){
        vehicle.leave();
    }
}
