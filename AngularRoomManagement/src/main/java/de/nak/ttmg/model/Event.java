package de.nak.ttmg.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by felixb on 27/10/15.
 */
@Entity
public class Event implements Serializable {

    private Long id;

    private Date begin;

    private Date end;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    @Column(nullable = false)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
