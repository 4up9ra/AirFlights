package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class RuleBasedFlightFilter implements FlightFilter {

    private final List<FilterRule> filterRule = new ArrayList<>();

    public void addRule(FilterRule rule) {
        filterRule.add(rule);
    }

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (filterRule.stream().allMatch(rule -> rule.isApplicable(flight))) {
                result.add(flight);
            }
        }
        return result;
    }
}
