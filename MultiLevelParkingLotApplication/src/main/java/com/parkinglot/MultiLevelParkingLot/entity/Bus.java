package com.parkinglot.MultiLevelParkingLot.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;

@Embeddable
@DiscriminatorValue("Bus")
public class Bus extends Vehicle {
    public Bus() {
        super(VehicleType.BUS);
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
