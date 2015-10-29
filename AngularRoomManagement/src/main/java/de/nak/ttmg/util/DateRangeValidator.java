package de.nak.ttmg.util;

import java.util.Date;

/**
 * Created by felixb on 28/10/15.
 */
public class DateRangeValidator {

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
