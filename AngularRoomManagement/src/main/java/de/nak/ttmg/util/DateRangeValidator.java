package de.nak.ttmg.util;

import de.nak.ttmg.model.DateRange;

import java.util.Date;

/**
 * Created by felixb on 28/10/15.
 * This class checks if a date range is valid.
 */
public class DateRangeValidator {

    /**
     * Tests if a date range is valid
     * @param start date to be tested
     * @param start date to be tested
     * @throws DateRangeException
     */
    public DateRange createValidRange(Date start, Date end) throws DateRangeException {
        if (start != null && end != null) {
            if (!end.after(start)) {
                throw new DateRangeException("End Date has to be after the start date");
            }
        } else if (start != null || end != null) {
            throw new DateRangeException("Start- and End-Date have to be set together.");
        } else if (start == null) {
            return null;
        }
        return new DateRange(start, end);
    }

    /**
     * Tests if a repeat count is valid
     * @param repeatCount to be tested
     * @throws InvalidParameterException if repeat count is below 0 or above 100
     */
    public void validateRepeatCount(Integer repeatCount) throws InvalidParameterException {
        if (repeatCount != null) {
            if (repeatCount < 0 || repeatCount > 100) {
                throw new InvalidParameterException("repeatCount",
                        InvalidParameterException.InvalidParameterType.INVALID_RANGE);
            }
        }
    }

}
