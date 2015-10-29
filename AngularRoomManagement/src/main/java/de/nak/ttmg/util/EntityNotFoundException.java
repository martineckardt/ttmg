package de.nak.ttmg.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by felixb on 28/10/15.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class EntityNotFoundException extends ValidationException {

    private final String type;
    private final Long id;
    public EntityNotFoundException(String type, Long id) {
        super("Entity of type " + type + " with id " + id + " was not found.");
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }
}
