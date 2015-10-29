package de.nak.ttmg.util;

import de.nak.ttmg.model.Tutor;

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
        validateName(tutor.getFirstName());
        validateName(tutor.getLastName());
        validateName(tutor.getTitle());
        validateChangeTime(tutor.getCustomChangeTime());
    }

    private void validateName(String name) throws ValidationException {
        if (name == null || name.isEmpty()) {
            throw new InvalidParameterException("name", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        boolean validCharacters = name.chars().allMatch(Character::isLetter);
        if (!validCharacters) {
            throw new InvalidParameterException("name", InvalidParameterException.InvalidParameterType.INVALID_FORMAT);
        }
    }

    private void validateChangeTime(Integer changeTime) throws ValidationException {
        if (changeTime == null) {
            throw new InvalidParameterException("changeTime", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        if (changeTime < 0 || changeTime > 1000) {
            throw new InvalidParameterException("changeTime", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }

}
