package com.thoughtworks.tdd;

import sun.security.krb5.internal.Ticket;

import java.util.List;

public class ParkingLotManager {
    private ParkingLot parkingLot;
    private List<ParkingBoy> parkingBoys;

    public ParkingLotManager(ParkingLot parkingLot, List<ParkingBoy> parkingBoys) {
        this.parkingLot = parkingLot;
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
