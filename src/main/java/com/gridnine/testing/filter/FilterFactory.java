package com.gridnine.testing.filter;

import java.time.LocalDateTime;

public class FilterFactory {

    public RuleBasedFlightFilter createDepartureBeforeDateFilter(LocalDateTime date)    {

        RuleBasedFlightFilter ruleBasedFlightFilter = new RuleBasedFlightFilter();

        ruleBasedFlightFilter.addRule(new DepartureBeforeDateRule(date));
        System.out.println("-----------------------------------------------------");
        System.out.println("Из списка исключены перелёты у которых вылет до текущего момента времени:");

        return ruleBasedFlightFilter;
    }

    public RuleBasedFlightFilter createDepartureBeforeArrivalFilter()    {

        RuleBasedFlightFilter ruleBasedFlightFilter = new RuleBasedFlightFilter();

        ruleBasedFlightFilter.addRule(new DepartureBeforeArrivalRule());
        System.out.println("-----------------------------------------------------");
        System.out.println("Из списка исключены перелёты у которых имеются сегменты с датой прилёта раньше даты вылета:");

        return ruleBasedFlightFilter;
    }

    public RuleBasedFlightFilter createLandedTimeMoreThanXHoursFilter(int hours)    {

        RuleBasedFlightFilter ruleBasedFlightFilter = new RuleBasedFlightFilter();

        ruleBasedFlightFilter.addRule(new LandedTimeMoreThanXHoursRule(hours));
        System.out.println("-----------------------------------------------------");
        System.out.println("Из списка исключены перелёты у которых общее время, проведённое на земле, превышает "
                + hours + ((hours == 1 || (hours - 11) % 10 == 0) && hours != 11 ? " час: "
                : hours % 10 > 1 && hours % 10 < 5 ? " часа: "
                :  " часов:"));

        return ruleBasedFlightFilter;
    }
}
