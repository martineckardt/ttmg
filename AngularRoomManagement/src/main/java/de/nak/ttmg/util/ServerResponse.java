package de.nak.ttmg.util;

import java.io.Serializable;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by felixb on 29/10/15.
 */
public class ServerResponse<T> implements Serializable{
        private T payload;
    private ValidationException exception;

    public ServerResponse() {
        payload = null;
        exception = null;
    }

    public ServerResponse(T payload) {
        this.payload = payload;
        this.exception = null;
    }

    public ServerResponse(ValidationException exception) {
        this.payload = null;
        this.exception = exception;
    }


    public T getPayload() {
        return payload;
    }

    public ValidationException getException() {
        return exception;
    }
}
