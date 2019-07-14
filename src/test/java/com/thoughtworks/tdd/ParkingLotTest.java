package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotTest {
    @Test
    public void  should_return_car_when_fetch_car_given_have_ticket_by_parking_the_car () {

        //Given
        Customer customer = new Customer();
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy (parkingLot);
        ParkingTicket ticket = parkingBoy.parkCar(car);

        //When
        Car fetchCar = parkingBoy.fetchCar(ticket,customer);

        // Then
        Assertions.assertSame(car, fetchCar);
    }

    @Test
    public void  should_return_null_when_fetch_car_given_have_wrong_ticket_to_fetch_the_car () {

        //Given

        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Customer customer = new Customer();
        ParkingBoy parkingBoy = new ParkingBoy (parkingLot);
        ParkingTicket corretTicket = parkingBoy.parkCar(car);
        ParkingTicket wrongTicket = new ParkingTicket(null);

        //When
        Car fetchCar = parkingBoy.fetchCar(wrongTicket,customer);

        // Then
        Assertions.assertSame(fetchCar, null);

    }

    @Test
    public void should_return_null_when_fetch_car_no_given_ticket_to_fetch_the_car () {

        //Given
        Customer customer = new Customer();
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy (parkingLot);
        ParkingTicket corretTicket = parkingBoy.parkCar(car);

        //When
        Car fetchCar = parkingBoy.fetchCar(null,customer);

        // Then
        Assertions.assertSame(fetchCar, null);

    }

    @Test
    public void  should_return_no_car_when_fetch_car_given_used_ticket () {

        //Given
        Customer customer = new Customer();
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy (parkingLot);
        ParkingTicket ticket = parkingBoy.parkCar(car);

        //When
        Car fetchCar1 = parkingBoy.fetchCar(ticket,customer);
        Car fetchCar2 = parkingBoy.fetchCar(ticket,customer);

        // Then
        Assertions.assertSame(null, fetchCar2);

    }

    @Test
    public void  should_return_no_ticket_when_park_car_given_more_then_limit_cars () {

        //Given
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy (parkingLot);
        for (int i = 0; i < 2; i++){
            Car car = new Car();
            parkingBoy.parkCar(car);
        }
        Car car3 = new Car();

        //When
        ParkingTicket ticket = parkingBoy.parkCar(car3);

        // Then
        Assertions.assertSame(null, ticket);
    }

    @Test
    public void should_return_null_when_park_car_given_null_car () {

        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy (parkingLot);

        //When
        ParkingTicket ticket = parkingBoy.parkCar(null);

        // Then
        Assertions.assertSame(null, ticket);
    }

    @Test
    public void should_return_message_about_unrecognized_parking_ticket_when_query_error_massage_given_used_ticket () {

        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy (parkingLot);
        Customer customer = new Customer();
        Car car = new Car();
        ParkingTicket ticket = parkingBoy.parkCar(car);
        parkingBoy.fetchCar(ticket,customer);


        //When
        parkingBoy.fetchCar(ticket,customer);
        String Mes = customer.queryErrorMessage();

        // Then
        Assertions.assertEquals("Unrecognized parking ticket",Mes);
    }

    @Test
    public void should_return_message_about_please_provide_your_parking_ticket_when_query_error_massage_given_no_ticket () {

        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy (parkingLot);
        Customer customer = new Customer();
        Car car = new Car();


        //When
        parkingBoy.fetchCar(null,customer);
        String Mes = customer.queryErrorMessage();

        // Then
        Assertions.assertEquals("Please provide your parking ticket.",Mes);
    }

}
