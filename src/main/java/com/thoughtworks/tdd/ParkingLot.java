package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Car> cars = new ArrayList<>();
    private List<ParkingTicket> tickets = new ArrayList<>();

    public ParkingTicket parkCar(Car car){
        cars.add(car);
        ParkingTicket ticket = new ParkingTicket(car);
        tickets.add(ticket);
        return ticket;
    }

    public Car fetchCar(ParkingTicket ticket){
        if (cars.contains(ticket.getCar())) {
            tickets.remove(ticket);
            cars.remove(ticket.getCar());
            return ticket.getCar();
        }
        else{
            return null;
        }
    }



}
