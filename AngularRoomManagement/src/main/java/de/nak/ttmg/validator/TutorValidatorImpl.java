package de.nak.ttmg.validator;

import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.exceptions.InvalidParameterException;
import de.nak.ttmg.exceptions.ValidationException;

/**
 * Created by felixb on 29/10/15.
 * This class checks if a tutor is valid.
 */
public class TutorValidatorImpl implements TutorValidator {

    /**
     * Checks if a tutor is valid
     * @param tutor to be tested
     * @throws ValidationException
     */
    @Override
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
        //Check the name with the regex
        boolean validCharacters = name.matches("[A-Z][. 'a-zA-Z0-9öäüÖÄÜ-]*");
        if (!validCharacters) {
            throw new InvalidParameterException(fieldName, InvalidParameterException.InvalidParameterType.INVALID_FORMAT);
        }
        //The name should be limited to 50 characters each
        if (name.length() > 50) {
            throw new InvalidParameterException(fieldName, InvalidParameterException.InvalidParameterType.INVALID_LENGTH);
        }
    }

    private void validateTitle(String title) throws ValidationException {
        if (title == null || title.trim().isEmpty()) {
            //Title may be null or empty (not all tutors have a title)
            return;
        }
        //Check the name with the regex
        boolean validCharacters = title.matches("[A-Z][. 'a-zA-Z0-9öäüÖÄÜ-]*");
        if (!validCharacters) {
            throw new InvalidParameterException("title", InvalidParameterException.InvalidParameterType.INVALID_FORMAT);
        }
        //The title should be limited to 50 characters
        if (title.length() > 50) {
            throw new InvalidParameterException("title", InvalidParameterException.InvalidParameterType.INVALID_LENGTH);
        }
    }

    private void validateChangeTime(Integer changeTime) throws ValidationException {
        //Change Time should not exceed 5 hours
        if (changeTime < 0 || changeTime > 300) {
            throw new InvalidParameterException("changeTime", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }
}
