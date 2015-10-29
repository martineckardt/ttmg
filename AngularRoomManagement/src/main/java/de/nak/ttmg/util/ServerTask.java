package de.nak.ttmg.util;

/**
 * Created by felixb on 29/10/15.
 */
public interface ServerTask<T> {

    T call() throws ValidationException;

}
