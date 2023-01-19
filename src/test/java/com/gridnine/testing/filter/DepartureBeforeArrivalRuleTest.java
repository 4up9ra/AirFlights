package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DepartureBeforeArrivalRuleTest {

    @Test
    public void shouldBeApplicableWithOneCorrectFlight() {
        //given
        DepartureBeforeArrivalRule departureBeforeArrivalRule = new DepartureBeforeArrivalRule();
        Segment segment = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        Flight flight = new Flight(segments);

        //when
        boolean applicable = departureBeforeArrivalRule.isApplicable(flight);

        //then
        assertTrue(applicable);
    }

    @Test
    public void shouldBeApplicableWithFiveCorrectFlight() {
        //given
        DepartureBeforeArrivalRule departureBeforeArrivalRule = new DepartureBeforeArrivalRule();
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5));
        Segment segment3 = new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(7));
        Segment segment4 = new Segment(LocalDateTime.now().plusHours(11), LocalDateTime.now().plusHours(20));
        Segment segment5 = new Segment(LocalDateTime.now().plusHours(22), LocalDateTime.now().plusHours(24));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        segments.add(segment3);
        segments.add(segment4);
        segments.add(segment5);
        Flight flight = new Flight(segments);

        //when
        boolean applicable = departureBeforeArrivalRule.isApplicable(flight);

        //then
        assertTrue(applicable);
    }

    @Test
    public void shouldNotBeApplicableWithOneCorrectAndOneIncorrectFlights() {
        //given
        DepartureBeforeArrivalRule departureBeforeArrivalRule = new DepartureBeforeArrivalRule();
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(1));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        Flight flight = new Flight(segments);

        //when
        boolean applicable = departureBeforeArrivalRule.isApplicable(flight);

        //then
        assertFalse(applicable);
    }

    @Test
    public void shouldNotBeApplicableWithArrivalDateBeforeDepartureDate() {
        //given
        DepartureBeforeArrivalRule departureBeforeArrivalRule = new DepartureBeforeArrivalRule();
        Segment segment = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now());
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        Flight flight = new Flight(segments);

        //when
        boolean applicable = departureBeforeArrivalRule.isApplicable(flight);

        //then
        assertFalse(applicable);
    }
}