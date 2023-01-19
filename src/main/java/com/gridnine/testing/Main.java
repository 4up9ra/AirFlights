package com.gridnine.testing;
import com.gridnine.testing.filter.*;
import com.gridnine.testing.flightfactory.FlightBuilder;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> allFlights = FlightBuilder.createFlights();
        System.out.println("Изначальный список:");
        allFlights.forEach(System.out::println);

        FilterFactory factory = new FilterFactory();

        FlightFilter departureBeforeDateFilter = factory.createDepartureBeforeDateFilter(LocalDateTime.now());
        List<Flight> departureBeforeDateFlights = departureBeforeDateFilter.filterFlights(allFlights);
        departureBeforeDateFlights.forEach(System.out::println);

        FlightFilter departureBeforeArrivalFilter = factory.createDepartureBeforeArrivalFilter();
        List<Flight> departureBeforeArrivalFlights =  departureBeforeArrivalFilter.filterFlights(allFlights);
        departureBeforeArrivalFlights.forEach(System.out::println);

        FlightFilter landedTimeMoreThanXHoursFilter = factory.createLandedTimeMoreThanXHoursFilter(2);
        List<Flight> landedTimeMoreThanXHoursFlights = landedTimeMoreThanXHoursFilter.filterFlights(allFlights);
        landedTimeMoreThanXHoursFlights.forEach(System.out::println);
    }
}
