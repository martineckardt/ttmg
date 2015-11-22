package de.nak.ttmg.validator;

import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.exceptions.InvalidParameterException;
import de.nak.ttmg.exceptions.ValidationException;

/**
 * Created by felixb on 29/10/15.
 * This class checks if a tutor is valid.
 */
public class TutorValidator {

    /**
     * Checks if a tutor is valid
     * @param tutor to be tested
     * @throws ValidationException
     */
    public void validateTutor(Tutor tutor) throws ValidationException {
        validateName(tutor.getFirstName(), "first");
        validateName(tutor.getLastName(), "last");
        validateTitle(tutor.getTitle());
        validateChangeTime(tutor.getCustomChangeTime());
    }

    private void validateName(String name, String fieldName) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidParameterException(fieldName, InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        boolean validCharacters = name.matches("[A-Z][. 'a-zA-Z0-9öäüÖÄÜ-]*");
        if (!validCharacters) {
            throw new InvalidParameterException(fieldName, InvalidParameterException.InvalidParameterType.INVALID_FORMAT);
        }
        if (name.length() > 50) {
            throw new InvalidParameterException(fieldName, InvalidParameterException.InvalidParameterType.INVALID_LENGTH);
        }
    }

    private void validateTitle(String title) throws ValidationException {
        if (title == null || title.trim().isEmpty()) {
            return;
        }
        boolean validCharacters = title.matches("[A-Z][. 'a-zA-Z0-9öäüÖÄÜ-]*");
        if (!validCharacters) {
            throw new InvalidParameterException("title", InvalidParameterException.InvalidParameterType.INVALID_FORMAT);
        }
        if (title.length() > 50) {
            throw new InvalidParameterException("title", InvalidParameterException.InvalidParameterType.INVALID_LENGTH);
        }
    }

    private void validateChangeTime(Integer changeTime) throws ValidationException {
        if (changeTime < 0 || changeTime > 300) {
            throw new InvalidParameterException("changeTime", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }
}