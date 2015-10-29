package de.nak.ttmg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by felixb on 28/10/15.
 */
@Entity
public class Tutor implements Serializable, HasAvailability {
    private static Integer defaultChangeTime = 15;

    private String firstName;
    private String lastName;
    private String title;
    private Integer changeTime;
    private Set<Course> courses = new HashSet<>();

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tutor_id", nullable = false)
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tutor")
    @JsonBackReference
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                title + " " + firstName + " " + lastName +
                ", changeTime=" + changeTime +
                '}';
    }

    @Transient
    @Override
    public String getReadableString() {
        StringBuilder sb = new StringBuilder();
        if (title != null && !title.isEmpty()) {
            sb.append(title);
            sb.append(" ");
        }
        if (firstName != null && !firstName.isEmpty()) {
            sb.append(firstName);
            sb.append(" ");
        }
        if (lastName != null && !lastName.isEmpty()) {
            sb.append(lastName);
        }
        return sb.toString().trim();
    }

    @Transient
    @Override
    public String getObjectType() {
        return "tutor";
    }

    @Transient
    @Override
    public Integer getCustomChangeTime() {
        if (getChangeTime() != null) {
            return getChangeTime();
        }
        return defaultChangeTime;
    }
}
