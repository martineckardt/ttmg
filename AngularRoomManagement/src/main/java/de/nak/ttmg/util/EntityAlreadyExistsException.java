package de.nak.ttmg.util;

/**
 * Created by felixb on 28/10/15.
 */
public class EntityAlreadyExistsException extends ValidationException {
    public EntityAlreadyExistsException() {
        super("Entity does already exist and cannot be recreated.");
    }
}
