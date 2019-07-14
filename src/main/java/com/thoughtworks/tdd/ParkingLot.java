package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Car> cars = new ArrayList<>();

    public ParkingTicket parkCar(Car car){
        cars.add(car);
        return new ParkingTicket(car);
    }

    public Car fetchCar(ParkingTicket ticket){
        if (cars.contains(ticket.getCar())) {
            return ticket.getCar();
        }
        else{
            return null;
        }
    }



}
