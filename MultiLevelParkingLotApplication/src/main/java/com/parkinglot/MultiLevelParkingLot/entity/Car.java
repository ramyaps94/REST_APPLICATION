package com.parkinglot.MultiLevelParkingLot.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;

@Embeddable
@DiscriminatorValue("Car")
public class Car extends Vehicle {
    public Car() {
        super(VehicleType.CAR);
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
