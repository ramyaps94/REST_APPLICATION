package com.parkinglot.MultiLevelParkingLot.entity;

import javax.persistence.*;

@Entity
@Table(name="PARKING_SLOT")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SLOT_NUMBER")
    long slotNumber;
    @Column(name = "FLOOR_NO")
    int floorNo;
    @Column(name = "OCCUPIED")
    boolean isOccupied;
    @Column(name = "SPOT_TYPE")
    ParkingSpotType spotType;
    @Embedded
    @ManyToOne
    @JoinTable(name="VEHICLE", joinColumns = @JoinColumn(name="VEHICLEID"), inverseJoinColumns = @JoinColumn(name="SLOT_NUMBER"))
    Vehicle vehicle;


    public Slot(ParkingSpotType type) {
        this.spotType = type;
    }

    public Slot(ParkingSpotType type,long slotNumber) {
        this.spotType = type;
        isOccupied = false;
        this.slotNumber = slotNumber;
    }
    Slot(long slotNumber) {
        isOccupied = false;
        this.slotNumber = slotNumber;
    }

    boolean isOccupied() {
        return isOccupied;
    }

    long getSlotNumber() {
        return slotNumber;
    }

    void park() {
        this.vehicle = vehicle;
        isOccupied = true;
    }

    void unPark() {
        this.vehicle = null;
        isOccupied = false;
    }

    @Override
    public boolean equals(Object o) {
        return (((Slot) o).slotNumber == this.slotNumber);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int)this.slotNumber;
        return hash;
    }

    public static ParkingSpotType mapVehicleToSpot(Vehicle vehicle){
        if(vehicle.getVehicleType().equals(VehicleType.BIKE))
            return ParkingSpotType.TWOWHEELER;
        else if(vehicle.getVehicleType().equals(VehicleType.CAR))
            return ParkingSpotType.FOURWHEELERCOMPACT;
        else
            return ParkingSpotType.FOURWHEELERLARGE;
    }

    public boolean canFitVehicle(Vehicle vehicle){
        if(vehicle.getVehicleType().equals(VehicleType.BUS)) {
            if(spotType.equals(ParkingSpotType.FOURWHEELERLARGE))
                return true;
            else
                return false;
        }
        else if(vehicle.getVehicleType().equals(VehicleType.CAR)) {
            if (spotType.equals(ParkingSpotType.FOURWHEELERLARGE) || spotType.equals(ParkingSpotType.FOURWHEELERCOMPACT))
                return true;
            else
                return false;
        }
            else if(vehicle.getVehicleType().equals(VehicleType.BIKE))
            return true;

            else
                return false;

    }

    public boolean isAvailable() { return vehicle == null; }

}

