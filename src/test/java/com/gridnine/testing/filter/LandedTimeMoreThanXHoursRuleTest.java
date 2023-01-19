package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LandedTimeMoreThanXHoursRuleTest {

    @Test
    public void shouldBeApplicableForFlightsWithLessThanTwoHoursLanded() {
        //given
        LandedTimeMoreThanXHoursRule landedTimeMoreThanXHoursRule = new LandedTimeMoreThanXHoursRule(2);
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(5));
        Segment segment3 = new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(7));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        segments.add(segment3);
        Flight flight = new Flight(segments);

        //when
        boolean applicable = landedTimeMoreThanXHoursRule.isApplicable(flight);

        //then
        assertTrue(applicable);
    }

    @Test
    public void shouldBeApplicableForOneFlight() {
        //given
        LandedTimeMoreThanXHoursRule landedTimeMoreThanXHoursRule = new LandedTimeMoreThanXHoursRule(2);
        Segment segment = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(10));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        Flight flight = new Flight(segments);

        //when
        boolean applicable = landedTimeMoreThanXHoursRule.isApplicable(flight);

        //then
        assertTrue(applicable);
    }

    @Test
    public void shouldNotBeApplicableForFlightsWithMoreThanTwoHoursLanded() {
        //given
        LandedTimeMoreThanXHoursRule landedTimeMoreThanXHoursRule = new LandedTimeMoreThanXHoursRule(2);
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(10));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        Flight flight = new Flight(segments);

        //when
        boolean applicable = landedTimeMoreThanXHoursRule.isApplicable(flight);

        //then
        assertFalse(applicable);
    }
}