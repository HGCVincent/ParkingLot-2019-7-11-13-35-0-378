package com.thoughtworks.tdd;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket parkCar(Car car) {
        return parkingLot.parkCar(car);
    }

    public Car fetchCar(ParkingTicket ticket) {
        if (ticket == null){
            return null;
        }
        else {
            return parkingLot.fetchCar(ticket);
        }
    }
}
