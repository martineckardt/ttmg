package de.nak.ttmg.model;

import java.util.Date;

/**
 * Created by felixb on 15/11/15.
 * This class creates Date Range objects
 */
public class DateRangeFactory {

    /**
     * Creates a date range between start and end date.
     * @param start date
     * @param end date
     * @return range or null if start is null
     */
    public static DateRange createDateRange(Date start, Date end) {
        if (start == null) {
            return null;
        }
        return new DateRange(start, end);
    }
}
