package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.temporal.ChronoUnit;

public class LandedTimeMoreThanXHoursRule implements FilterRule {

    private final int hours;

    public LandedTimeMoreThanXHoursRule(int hours) {
        this.hours = hours;
    }

    @Override
    public boolean isApplicable(Flight flight) {
        long landedTime = 0;
        for (int i = 0; i < flight.getSegments().size() - 1; i++) {
            landedTime += flight.getSegments().get(i).getArrivalDate()
                    .until(flight.getSegments().get(i + 1).getDepartureDate(), ChronoUnit.HOURS);
        }
        return landedTime < hours;
    }
}
