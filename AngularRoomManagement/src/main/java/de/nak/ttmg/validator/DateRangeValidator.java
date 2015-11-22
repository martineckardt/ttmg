package de.nak.ttmg.validator;

import de.nak.ttmg.util.Constants;
import de.nak.ttmg.exceptions.DateRangeException;
import de.nak.ttmg.exceptions.InvalidParameterException;

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
            if (!end.after(start)) {
                throw new DateRangeException(Constants.DATE_RANGE_START_AFTER_END);
            }
        } else if (start != null || end != null) {
            throw new DateRangeException(Constants.DATE_RANGE_ONE_NULL);
        }
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