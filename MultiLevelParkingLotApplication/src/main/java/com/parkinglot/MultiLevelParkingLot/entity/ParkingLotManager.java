package com.parkinglot.MultiLevelParkingLot.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="PARKING_LOT")
public class ParkingLotManager {

    @Id
    @Column(name="LOT_NUMBER")
    int lotNumber;
    //Vehicle vehicle;
    //Slot slot;

//    ParkingLotManager(Vehicle vehicle,Slot slot){
//        this.vehicle = vehicle;
//        this.slot = slot;
//    }

    private static ParkingLotManager lot = null;

    private static final int NUMBER_OF_SMALL_SLOTS = 5;
    private static final int NUMBER_OF_COMPACT_SLOTS = 2;
    private static final int NUMBER_OF_LARGE_SLOTS = 3;

    @Embedded
    public Map<Integer, Slot> occupiedSlots;
    @Embedded
    private List<Slot> smallSlots;
    @Embedded
    private List<Slot> compactSlots;
    @Embedded
    private List<Slot> largeSlots;

    private ParkingLotManager() {
        smallSlots = new LinkedList<>();
        compactSlots = new LinkedList<>();
        largeSlots = new LinkedList<>();
        occupiedSlots = new HashMap<>();
        for (int i = 1; i <= NUMBER_OF_SMALL_SLOTS; i++)
            smallSlots.add(new Slot(ParkingSpotType.TWOWHEELER,i));

        for (int i = 1; i <= NUMBER_OF_COMPACT_SLOTS; i++)
            compactSlots.add(new Slot(ParkingSpotType.FOURWHEELERCOMPACT,i));

        for (int i = 1; i <= NUMBER_OF_LARGE_SLOTS; i++)
            largeSlots.add(new Slot(ParkingSpotType.FOURWHEELERLARGE,i));

    }
    public List<Slot> getSmallSlots() {
        return smallSlots;
    }

    public List<Slot> getCompactSlots() {
        return compactSlots;
    }

    public List<Slot> getLargeSlots() {
        return largeSlots;
    }

    public static ParkingLotManager getInstance() {
        if (lot == null)
            lot = new ParkingLotManager();
        return lot;
    }
}
