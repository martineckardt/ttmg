package de.nak.ttmg.validator;

import de.nak.ttmg.exceptions.DateRangeException;

import java.util.Date;

/**
 * Created by Martin Eckardt on 27.11.2015.
 */
public interface DateRangeValidator {
    void validateRange(Date start, Date end) throws DateRangeException;
}
