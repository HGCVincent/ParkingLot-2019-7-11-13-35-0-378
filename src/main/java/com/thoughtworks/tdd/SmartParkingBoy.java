package com.thoughtworks.tdd;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingLot chooseTheMoreEmptyParkingLot(){
        List<Integer> capacityList = getParkingLots().stream().map(e -> e.getCapacity()).collect(Collectors.toList());
        int index = capacityList.indexOf(Collections.max(capacityList));
        return getParkingLots().get(index);
    }
}
