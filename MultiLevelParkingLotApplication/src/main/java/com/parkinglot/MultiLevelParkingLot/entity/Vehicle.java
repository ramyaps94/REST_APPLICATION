package com.parkinglot.MultiLevelParkingLot.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
//@MappedSuperclass
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="vehicletype",
        discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("Vehicle")
@Table(name="VEHICLE")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="VEHICLEID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long vehicleId;

    @Column(name = "LICENSE_NUMBER")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String vehicleLicenseNumber;

    @Column(name = "COLOR")
    private String color;

    @Column(name="NAME")
    private String name;

    @Column(name="VEHICLE_TYPE")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VehicleType vehicleType;

    @Embedded
    @ManyToOne
    @JoinTable(name="PARKING_LOT", joinColumns = @JoinColumn(name="VEHICLEID"), inverseJoinColumns = @JoinColumn(name="LOT_NUMBER"))
    private ParkingLotManager lot;
    public Vehicle(){}

    public Vehicle(VehicleType type) {
        this.vehicleType = type;
        lot = ParkingLotManager.getInstance();
    }

    private Slot findSlot() {

        Slot slot;
        if (this.vehicleType.equals(VehicleType.BIKE)) {

            slot = lot.getSmallSlots().remove(0);
        }
        else if (this.vehicleType.equals(VehicleType.CAR)) {

            slot = lot.getCompactSlots().remove(0);
        }
        else if(this.vehicleType.equals(VehicleType.BUS)) {

            slot = lot.getLargeSlots().remove(0);
        }
            else
                slot = null;

        return slot;
    }

    public boolean park() {
        Slot slot = findSlot();
        if (slot != null) {
            lot.occupiedSlots.put(this.vehicleId.intValue(),slot);
            slot.park();
            return true;
        }
        return false;
    }

    public void leave() {
        Slot slot = lot.occupiedSlots.remove(this.vehicleId);
        slot.unPark();
        if (this.vehicleType.equals(VehicleType.BIKE)) {
            lot.getSmallSlots().add(slot);
        } else if (this.vehicleType.equals(VehicleType.CAR)) {

            lot.getCompactSlots().add(slot);
        } else if (this.vehicleType.equals(VehicleType.BUS)) {

            lot.getLargeSlots().add(slot);
        }
    }
    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleLicenseNumber(){
        return vehicleLicenseNumber;
    }

    public void setVehicleLicenseNumber(String vehicleLicenseNumber){
        this.vehicleLicenseNumber = vehicleLicenseNumber;
    }

    public VehicleType getVehicleType(){
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType){
        this.vehicleType = vehicleType;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

