package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DepartureBeforeDateRuleTest {

    @Test
    public void shouldBeApplicableForFlightWithDepartureTimeAfterCurrentTime() {
        //given
        LocalDateTime date = LocalDateTime.now();
        DepartureBeforeDateRule departureBeforeDateRule = new DepartureBeforeDateRule(date);
        Segment segment = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        Flight flight = new Flight(segments);

        //when
        boolean applicable = departureBeforeDateRule.isApplicable(flight);

        //then
        assertTrue(applicable);
    }

    @Test
    public void shouldNotBeApplicableWithSameTime() {
        //given
        LocalDateTime date = LocalDateTime.now();
        DepartureBeforeDateRule departureBeforeDateRule = new DepartureBeforeDateRule(date);
        Segment segment = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        Flight flight = new Flight(segments);

        //when
        boolean applicable = departureBeforeDateRule.isApplicable(flight);

        //then
        assertFalse(applicable);
    }

    @Test
    public void shouldNotBeApplicableWithFlightBeforeCurrentTime() {
        //given
        LocalDateTime date = LocalDateTime.now();
        DepartureBeforeDateRule departureBeforeDateRule = new DepartureBeforeDateRule(date);
        Segment segment1 = new Segment(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(2));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        Flight flight = new Flight(segments);

        //when
        boolean applicable = departureBeforeDateRule.isApplicable(flight);

        //then
        assertFalse(applicable);
    }
}