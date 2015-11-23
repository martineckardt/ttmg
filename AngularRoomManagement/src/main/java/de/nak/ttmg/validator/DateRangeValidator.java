package de.nak.ttmg.validator;

import de.nak.ttmg.util.Constants;
import de.nak.ttmg.exceptions.DateRangeException;

import java.util.Date;

/**
 * Created by felixb on 28/10/15.
 * This class checks if a date range is valid.
 */
public class DateRangeValidator {

    /**
     * Tests if a date range is valid
     * @param start date to be tested
     * @param end date to be tested
     * @throws DateRangeException
     */
    public void validateRange(Date start, Date end) throws DateRangeException {
        if (start != null && end != null) {
            //Verify if end is before the start date
            if (!end.after(start)) {
                throw new DateRangeException(Constants.DATE_RANGE_START_AFTER_END);
            }
        } else if (start != null || end != null) {
            throw new DateRangeException(Constants.DATE_RANGE_ONE_NULL);
        }
    }
}
