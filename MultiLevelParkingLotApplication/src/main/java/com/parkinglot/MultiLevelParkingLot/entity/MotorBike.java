package com.parkinglot.MultiLevelParkingLot.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;

@Embeddable
@DiscriminatorValue("Motorbike")
public class MotorBike extends Vehicle {
    public MotorBike() {
        super(VehicleType.BIKE);
    }

//    private Long vehicleId;
//    public Long getVehicleId() {
//        return vehicleId;
//    }
//
//    public void setVehicleId(Long vehicleId) {
//        this.vehicleId = vehicleId;
//    }
}
