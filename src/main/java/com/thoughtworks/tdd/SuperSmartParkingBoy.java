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

    @Override
    public ParkingTicket parkCar(Car car, Customer customer) {
        ParkingTicket ticket = null;
        ParkingLot selectedParkingLot = chooseLargerAvailablePositionRateParkingLot();
        if (car == null) {
            return ticket;
        }else if (selectedParkingLot.getCapacity() == 0){
            customer.setErrorMessage("Not enough position.");
        }else{
            ticket = selectedParkingLot.parkCar(car);
        }
        return ticket;
    }



}
