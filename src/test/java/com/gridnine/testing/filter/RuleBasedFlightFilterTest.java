package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RuleBasedFlightFilterTest {

    @Test
    public void shouldAddRule() {
        //given
         List<FilterRule> filterRule = new ArrayList<>();

        //when
        filterRule.add(new DepartureBeforeArrivalRule());

        //then
        assertNotNull(filterRule);
        assertFalse(filterRule.isEmpty());
    }

    @Test
    public void shouldFilterFlights() {
        //given
        RuleBasedFlightFilter ruleBasedFlightFilter = new RuleBasedFlightFilter();
        List<Flight> flights = new ArrayList<>();
        List<FilterRule> filterRule = new ArrayList<>();
        LandedTimeMoreThanXHoursRule landedTimeMoreThanXHoursRule = new LandedTimeMoreThanXHoursRule(1);
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(5));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        Flight flight = new Flight(segments);
        flights.add(flight);
        filterRule.add(landedTimeMoreThanXHoursRule);

        //when
        List<Flight> result = ruleBasedFlightFilter.filterFlights(flights);

        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}