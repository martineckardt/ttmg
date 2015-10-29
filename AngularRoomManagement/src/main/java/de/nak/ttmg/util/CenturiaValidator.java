package de.nak.ttmg.util;

import de.nak.ttmg.model.Centuria;

/**
 * Created by felixb on 30/10/15.
 * This class checks if a centuria is valid.
 */
public class CenturiaValidator {

    /**
     * Validates if a centuria has valid properties only.
     * @param centuria to be tested
     * @throws ValidationException
     */
    public void validateCenturia(Centuria centuria) throws ValidationException{
        validateYear(centuria.getYear());
        validateLetter(centuria.getLetter());
        validateNbrOfStudents(centuria.getNbrOfStudents());
    }

    private void validateYear(Integer year) throws ValidationException {
        if (year == null) {
            throw new InvalidParameterException("year", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        if (year < 1970 || year > 2100) {
            throw new InvalidParameterException("year", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }

    private void validateLetter(Character letter) {
        char[] chars = "abcd".toCharArray();
        for (char aChar : chars) {
            if (letter == aChar) {
                return;
            }
        }
        throw new InvalidParameterException("letter", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
    }

    private void validateNbrOfStudents(Integer students) {
        if (students == null) {
            throw new InvalidParameterException("students", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        if (students < 0 || students > 1000) {
            throw new InvalidParameterException("students", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }
}
