package de.nak.ttmg.util;

/**
 * Created by felixb on 28/10/15.
 * This Exception is thrown if an object does already exist in the db or if some constrains can not be set.
 */
public class EntityAlreadyExistsException extends ValidationException {
    public EntityAlreadyExistsException() {
        super(Constants.ENTITY_ALREADY_EXISTS);
    }
}
