package de.nak.ttmg.model;

import java.util.Date;

/**
 * Created by felixb on 15/11/15.
 * This class describes a date range with a start and an end date.
 */
public class DateRange {

    private Date begin;
    private Date end;

    /**
     * Creates a date range with a begin and an end date.
     * @param begin
     * @param end
     */
    public DateRange(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    /**
     * Creates a date range from a daterange with an offset of X weeks
     * @param range that should be moved by X weeks
     * @param weekOffset number of weeks the the range should be moved
     */
    public DateRange(DateRange range, Integer weekOffset) {
        this.begin = new Date(range.getBegin().getTime() + weekOffset*7*24*60*60);
        this.end = new Date(range.getEnd().getTime() + weekOffset*7*24*60*60);
    }

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }
}
