package de.nak.ttmg.model;

import de.nak.ttmg.util.DateRangeException;
import de.nak.ttmg.util.DateRangeValidator;
import de.nak.ttmg.util.InvalidParameterException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by felixb on 15/11/15.
 * This class describes a date range with a start and an end date.
 */
public class DateRange implements HasReadableString{

    private final Date begin;

    private final Date end;

    private static final DateRangeValidator rangeValidator = new DateRangeValidator();

    /**
     * Creates a date range with a begin and an end date.
     * @param begin date
     * @param end date
     */
    public DateRange(Date begin, Date end) throws DateRangeException{
        rangeValidator.validateRange(begin,end);
        this.begin = begin;
        this.end = end;
    }

    /**
     * Creates a date range from a date range with an offset of X weeks
     * @param range that should be moved by X weeks, must not be null
     * @param weekOffset number of weeks the the range should be moved
     */
    public DateRange(DateRange range, Integer weekOffset) throws InvalidParameterException{
        rangeValidator.validateRepeatCount(weekOffset);
        if (weekOffset == null) {
            weekOffset = 0;
        }
        this.begin = new Date(range.getBegin().getTime() + weekOffset*7*24*60*60);
        this.end = new Date(range.getEnd().getTime() + weekOffset*7*24*60*60);
    }

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }


    @Override
    public String getReadableString() {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd.MM.yy HH.mm");
        String start = dt1.format(getBegin());
        String end;
        if (isSameDay()) {
            SimpleDateFormat dt2 = new SimpleDateFormat("HH.mm");
            end = dt2.format(getEnd());
        } else {
            end = dt1.format(getEnd());
        }
        return start + " - " + end;
    }

    private boolean isSameDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBegin());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(getEnd());
        int year1 = cal.get(Calendar.YEAR);
        int month1 = cal.get(Calendar.MONTH);
        int day1 = cal.get(Calendar.DAY_OF_MONTH);

        return year == year1 && month == month1 && day == day1;
    }
}
