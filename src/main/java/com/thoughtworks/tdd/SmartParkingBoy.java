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

    @Override
    public ParkingTicket parkCar(Car car, Customer customer) {
        ParkingTicket ticket = null;
        ParkingLot selectedParkingLot = chooseTheMoreEmptyParkingLot();
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
