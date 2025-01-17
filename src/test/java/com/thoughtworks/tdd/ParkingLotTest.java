package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.security.krb5.internal.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLotTest {
    @Test
    public void  should_return_car_when_fetch_car_given_have_ticket_by_parking_the_car () {

        //Given
        Customer customer = new Customer();
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy (parkingLot);
        ParkingTicket ticket = parkingBoy.parkCar(car,customer);

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
        ParkingTicket corretTicket = parkingBoy.parkCar(car,customer);
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
        ParkingTicket corretTicket = parkingBoy.parkCar(car,customer);

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
        ParkingTicket ticket = parkingBoy.parkCar(car,customer);

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
            Customer customer = new Customer();
            Car car = new Car();
            parkingBoy.parkCar(car,customer);
        }
        Customer customer3 =new Customer();
        Car car3 = new Car();

        //When
        ParkingTicket ticket = parkingBoy.parkCar(car3,customer3);

        // Then
        Assertions.assertSame(null, ticket);
    }

    @Test
    public void should_return_null_when_park_car_given_null_car () {

        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy (parkingLot);
        Customer customer = new Customer();

        //When
        ParkingTicket ticket = parkingBoy.parkCar(null,customer);

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
        ParkingTicket ticket = parkingBoy.parkCar(car,customer);
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

    @Test
    public void should_return_message_about_not_enough_position_when_query_error_massage_given_capacity_is_full () {

        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy (parkingLot);
        Customer customer1 = new Customer();
        Car car1 = new Car();
        parkingBoy.parkCar(car1,customer1);
        Customer customer2 =new Customer();
        Car car2 = new Car();

        //When
        parkingBoy.parkCar(car2,customer2);
        String Mes = customer2.queryErrorMessage();

        // Then
        Assertions.assertEquals("Not enough position.",Mes);
    }

    @Test
    public void should_return_ticket_when_petch_car_given_first_parking_lot_is_full() {

        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLots.add(parkingLot);
        }
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car1 = new Car();
        Customer customer1 = new Customer();
        parkingBoy.parkCar(car1,customer1);
        Car car2 = new Car();
        Customer customer2 = new Customer();

        //When
        ParkingTicket parkingTicket = parkingBoy.parkCar(car2,customer2);

        // Then
        Assertions.assertNotNull(parkingTicket);

    }

    @Test
    public void should_return_second_parking_lot_when_smart_parking_boy_choose_the_more_empty_parking_lot_given_first_parking_lot_capacity_is_1_and__second_parking_lot_capacity_is_2() {

        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        //When
        ParkingLot result = smartParkingBoy.chooseTheMoreEmptyParkingLot();

        // Then
        Assertions.assertSame(parkingLot2,result);

    }

    @Test
    public void should_park_the_car_to_second_lot_when_smart_parking_boy_park_car_given_first_parking_lot_capacity_is_1_and__second_parking_lot_capacity_is_2() {

        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        Customer customer = new Customer();

        //When
        ParkingTicket ticket = smartParkingBoy.parkCar(car,customer);

        // Then
        Assertions.assertEquals(true,parkingLot2.getTickets().contains(ticket));

    }

    @Test
    public void should_return_second_parking_lot_when_super_smart_parking_boy_choose_the_lager_avaliable_position_rate_given_first_parking_lot_position_rate_smaller_then_second_parking_lot_position_rate() {

        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLot1.parkCar(car1);
        parkingLot2.parkCar(car2);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        //When
        ParkingLot result = smartParkingBoy.chooseTheMoreEmptyParkingLot();

        // Then
        Assertions.assertSame(parkingLot2,result);

    }

    @Test
    public void should_park_the_car_to_second_lot_when_super_smart_parking_boy_park_car_given_first_parking_lot_position_rate_smaller_then_second_parking_lot_position_rate() {

        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLot1.parkCar(car1);
        parkingLot2.parkCar(car2);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        Customer customer = new Customer();

        //When
        ParkingTicket ticket = smartParkingBoy.parkCar(car,customer);

        // Then
        Assertions.assertEquals(true,parkingLot2.getTickets().contains(ticket));

    }

    @Test
    public void should_return_ticket_when_manage1_add_parking_boy_to_park_car_given_2_parking_lot_and_have_ticket_by_parking_the_car() {

        // given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> parkingLots1 = new ArrayList<>();
        List<ParkingLot> parkingLots2 = new ArrayList<>();
        parkingLots1.add(parkingLot1);

        Car car = new Car();
        Customer customer = new Customer();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots1);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLots1, parkingBoys);

        // when
        ParkingTicket ticket = parkingLotManager.chooseParkingBoyToPark(parkingBoy, car, customer);

        //then
        Assertions.assertTrue(parkingLot1.getTickets().contains(ticket));

    }

    @Test
    public void should_return_message_to_manager_about_unrecognized_ticket_when_manager_fetch_car_given_wrong_ticket() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);

        Customer customer =new Customer();
        Car car = new Car();

        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLots, parkingBoys);
        ParkingTicket parkingTicket = parkingLotManager.chooseParkingBoyToPark(parkingBoy,car,customer);
        parkingLotManager.chooseParkingBoyToFetch(parkingBoy,parkingTicket,customer);

        //When
        Car fetchCar = parkingLotManager.chooseParkingBoyToFetch(parkingBoy,parkingTicket,customer);
        String result = customer.queryErrorMessage();

        // Then
        Assertions.assertSame("Unrecognized parking ticket", result);
    }

    @Test
    public void should_return_message_to_manager_about_provide_ticket_when_manager_choose_parking_boy_fetch_car_given_have_no_ticket() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLots.add(parkingLot);
        Car car = new Car();
        Customer customer = new Customer();
        List<ParkingBoy> parkingBoys = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoys.add(parkingBoy);

        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLots, parkingBoys);
        ParkingTicket ticket = parkingLotManager.chooseParkingBoyToPark(parkingBoy,car,customer);

        //When
        parkingLotManager.chooseParkingBoyToFetch(parkingBoy,null,customer);

        // Then
        Assertions.assertSame("Please provide your parking ticket.", customer.queryErrorMessage());
    }

}
