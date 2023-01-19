package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

public class DepartureBeforeArrivalRule implements FilterRule {

    @Override
    public boolean isApplicable(Flight flight) {

        return flight.getSegments().stream()
                .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate()));
    }
}
