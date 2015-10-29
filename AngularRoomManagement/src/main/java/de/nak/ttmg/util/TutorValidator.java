package de.nak.ttmg.util;

import de.nak.ttmg.model.Tutor;

/**
 * Created by felixb on 29/10/15.
 */
public class TutorValidator {

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
        boolean validCharacters = name.chars().allMatch(x -> Character.isLetter(x));
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
