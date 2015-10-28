package de.nak.ttmg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by felixb on 28/10/15.
 */
@Entity
public class Tutor {
    private String firstName;
    private String lastName;
    private String title;
    private Integer changeTime;

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}