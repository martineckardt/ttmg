package de.nak.ttmg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by felixb on 28/10/15.
 */
@Entity
public class Tutor implements Serializable{
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Integer changeTime) {
        this.changeTime = changeTime;
    }
}
