package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void addParkingLots(ParkingLot parkingLots) {
        this.parkingLots.add(parkingLots);
    }

    public ParkingTicket parkCar(Car car, Customer customer) {
        ParkingTicket ticket = null;
        if (car == null) {
            return null;
        }
        for (ParkingLot parkingLot : parkingLots) {
            ticket = parkingLot.parkCar(car);
            if (ticket != null){
                return ticket;
            }
            if (ticket == null && parkingLot == parkingLots.get(parkingLots.size() - 1)) {
                customer.setErrorMessage("Not enough position.");
            }
        }
        return ticket;
    }

    public Car fetchCar(ParkingTicket ticket,Customer customer) {
        Car car = null;
        if (ticket == null){
            customer.setErrorMessage("Please provide your parking ticket.");
            return null;
        }
        for (ParkingLot parkingLot : parkingLots){
            car = parkingLot.fetchCar(ticket);
            if (car == null){
                customer.setErrorMessage("Unrecognized parking ticket");
            }
        }
        return car;
    }
}
