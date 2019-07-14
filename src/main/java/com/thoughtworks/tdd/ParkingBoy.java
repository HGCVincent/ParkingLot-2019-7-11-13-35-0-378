package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket parkCar(Car car) {
        if (car == null){
            return null;
        }
        return parkingLot.parkCar(car);
    }

    public Car fetchCar(ParkingTicket ticket,Customer customer) {
        if (ticket == null){
            customer.setErrorMessage("Please provide your parking ticket.");
            return null;
        }
        else {
            Car car = parkingLot.fetchCar(ticket);
            if (car == null){
                customer.setErrorMessage("Unrecognized parking ticket");
            }
            return car;
        }
    }
}
