package de.nak.ttmg.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by felixb on 27/10/15.
 */
@Entity
public class Event implements Serializable {

    private Date begin;

    private Date end;

}
