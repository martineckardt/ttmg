package de.nak.ttmg.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.nak.ttmg.util.Constants;

/**
 * Created by felixb on 28/10/15.
 * This Exception is thrown if a requested object could not be found in the db.
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

    @Override
    public String getLocalizableMessage() {
        return Constants.ENTITY_NOT_FOUND;
    }
}
