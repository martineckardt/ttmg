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

    /**
     * Creates a date range from a date range with an off set
     * @param range original date range.
     * @param offset in weeks
     * @return new range or null if original range is null
     */
    public static DateRange createDateRangeWithOffset(DateRange range, Integer offset) {
        if (range == null) {
            return null;
        }
        return new DateRange(range, offset);
    }

}
