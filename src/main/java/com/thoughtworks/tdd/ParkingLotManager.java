package com.thoughtworks.tdd;

import sun.security.krb5.internal.Ticket;

import java.util.List;

public class ParkingLotManager extends ParkingBoy{
    private List<ParkingBoy> parkingBoys;

    public ParkingLotManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.parkingBoys = parkingBoys;
    }

    public ParkingTicket chooseParkingBoyToPark(ParkingBoy parkingBoy, Car car,Customer customer) {
        if (this.parkingBoys.contains(parkingBoy)) {
            return parkingBoy.parkCar(car,customer);
        }
        return null;
    }

    public Car chooseParkingBoyToFetch(ParkingBoy parkingBoy, ParkingTicket parkingTicket,Customer customer) {
        if (this.parkingBoys.contains(parkingBoy)) {
            return parkingBoy.fetchCar(parkingTicket,customer);
        }
        return null;
    }

}
