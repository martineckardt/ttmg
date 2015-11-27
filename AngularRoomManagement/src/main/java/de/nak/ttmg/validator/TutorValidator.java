package de.nak.ttmg.validator;

import de.nak.ttmg.exceptions.ValidationException;
import de.nak.ttmg.model.Tutor;

/**
 * Created by Martin Eckardt on 27.11.2015.
 */
public interface TutorValidator {
    void validateTutor(Tutor tutor) throws ValidationException;
}
