package de.nak.ttmg.validator;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.exceptions.InvalidParameterException;
import de.nak.ttmg.exceptions.ValidationException;
import de.nak.ttmg.model.StudyProgram;

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
        validateProgram(centuria.getProgram());
        validateNbrOfStudents(centuria.getNbrOfStudents());
        validateChangeTime(centuria.getChangeTime());
    }

    /**
     * Validates if a year is between 2000 and 2099
     * @param year to be tested
     * @throws ValidationException
     */
    public void validateYear(Integer year) throws ValidationException {
        if (year == null) {
            throw new InvalidParameterException("year", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        //the year should be between 2000 and 2099
        if (year < 2000 || year > 2099) {
            throw new InvalidParameterException("year", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }

    private void validateLetter(Character letter) {
        if (!Character.isLetter(letter)) {
            throw new InvalidParameterException("letter", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }

    private void validateProgram(StudyProgram program) {
        if (program == null) {
            throw new InvalidParameterException("studyProgram",
                    InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
    }

    private void validateNbrOfStudents(Integer students) {
        if (students == null) {
            throw new InvalidParameterException("students", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        //Centurias should not have more than 9999 students
        if (students < 0 || students > 9999) {
            throw new InvalidParameterException("students", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }

    private void validateChangeTime(Integer changeTime) {
        if (changeTime != null) {
            //Change Time should not exceed 5 hours
            if (changeTime < 0 || changeTime > 300) {
                throw new InvalidParameterException("changeTime", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
            }
        }
    }
}
