package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Car> cars = new ArrayList<>();
    private List<ParkingTicket> tickets = new ArrayList<>();
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public ParkingTicket parkCar(Car car){
        if (isFull()){
            return null;
        }
        else {
            cars.add(car);
            ParkingTicket ticket = new ParkingTicket(car);
            tickets.add(ticket);
            return ticket;
        }
    }

    public Car fetchCar(ParkingTicket ticket){
        if (cars.contains(ticket.getCar())) {
            tickets.remove(ticket);
            cars.remove(ticket.getCar());
            capacity--;
            return ticket.getCar();
        }
        else{
            return null;
        }
    }

    public boolean isFull(){
        if (cars.size() >= capacity){
            return true;
        }
        else{
            return false;
        }
    }




}
