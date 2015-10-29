package de.nak.ttmg.util;

import java.io.Serializable;

/**
 * Created by felixb on 29/10/15.
 */
public class ServerResponse<T> implements Serializable{
        private T payload;
    private ValidationException generalException;
    private TimeConflictException timeConflictException;
    private EntityNotFoundException entityNotFoundException;
    private DateRangeException dateRangeException;
    private InvalidParameterException invalidParameterException;
    private EntityAlreadyExistsException entityAlreadyExistsException;

    public ServerResponse() {
        payload = null;
        this.generalException = null;
        this.timeConflictException = null;
        this.entityAlreadyExistsException = null;
        this.entityNotFoundException = null;
        this.dateRangeException = null;
        this.invalidParameterException = null;
    }

    public ServerResponse(ServerTask<T> callback) {
        this.generalException = null;
        this.timeConflictException = null;
        this.entityAlreadyExistsException = null;
        this.entityNotFoundException = null;
        this.dateRangeException = null;
        this.invalidParameterException = null;
        try {
            this.payload = callback.call();
        } catch (TimeConflictException e) {
            this.timeConflictException = e;
        } catch (EntityAlreadyExistsException e) {
            this.entityAlreadyExistsException = e;
        } catch (DateRangeException e) {
            this.dateRangeException = e;
        } catch (InvalidParameterException e) {
            this.invalidParameterException = e;
        } catch (EntityNotFoundException e) {
            this.entityNotFoundException = e;
        } catch (ValidationException e) {
            this.generalException = e;
        }
    }


    public T getPayload() {
        return payload;
    }

    public ValidationException getGeneralException() {
        return generalException;
    }

    public TimeConflictException getTimeConflictException() {
        return timeConflictException;
    }

    public EntityNotFoundException getEntityNotFoundException() {
        return entityNotFoundException;
    }

    public DateRangeException getDateRangeException() {
        return dateRangeException;
    }

    public InvalidParameterException getInvalidParameterException() {
        return invalidParameterException;
    }

    public EntityAlreadyExistsException getEntityAlreadyExistsException() {
        return entityAlreadyExistsException;
    }
}
