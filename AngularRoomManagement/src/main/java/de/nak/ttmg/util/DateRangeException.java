package de.nak.ttmg.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by felixb on 28/10/15.
 * This exception is thrown if a date range is invalid (e.g. negative)
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class DateRangeException extends ValidationException {
    public DateRangeException(String msg) {
        super(msg);
    }
}
