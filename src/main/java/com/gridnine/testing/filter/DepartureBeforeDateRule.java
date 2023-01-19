package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;

public class DepartureBeforeDateRule implements FilterRule {
    private final LocalDateTime date;

    public DepartureBeforeDateRule(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean isApplicable(Flight flight) {

    return flight.getSegments().stream()
                .allMatch(segment -> segment.getDepartureDate().isAfter(date));
    }
}
