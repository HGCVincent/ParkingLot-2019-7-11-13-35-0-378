package com.thoughtworks.tdd;

public class ParkingLot {
    private Car car;

    public ParkingTicket parkCar(Car car){
        this.car = car;
        return new ParkingTicket();
    }

    public Car fetchCar(ParkingTicket ticket){
        return car;
    }
}
