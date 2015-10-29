package de.nak.ttmg.util;

import java.util.Date;

/**
 * Created by felixb on 28/10/15.
 * This class checks if a course is valid.
 */
public class DateRangeValidator {

    /**
     * Tests if a date range is valid
     * @param start date to be tested
     * @param end date to be tested
     * @throws DateRangeException
     */
    public static void validateDateRange(Date start, Date end) throws DateRangeException {
        if (start != null && end != null) {
            if (!end.after(start)) {
                throw new DateRangeException("End Date has to be after the start date");
            }
        } else if (start != null || end != null){
            throw new DateRangeException("Start- and End-Date have to be set together.");
        }
    }

}
