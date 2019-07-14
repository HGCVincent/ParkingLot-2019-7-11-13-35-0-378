package com.thoughtworks.tdd;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingLot chooseLargerAvailablePositionRateParkingLot(){
        List<Integer> capacityList = getParkingLots().stream().map(e -> e.getCapacity()/e.getTotalCapacity()).collect(Collectors.toList());
        int index = capacityList.indexOf(Collections.max(capacityList));
        return getParkingLots().get(index);
    }

}
