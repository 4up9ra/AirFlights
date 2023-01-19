package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

public interface FilterRule {

    boolean isApplicable(Flight flight);

}
