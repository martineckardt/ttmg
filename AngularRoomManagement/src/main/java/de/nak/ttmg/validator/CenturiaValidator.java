package de.nak.ttmg.validator;

import de.nak.ttmg.exceptions.ValidationException;
import de.nak.ttmg.model.Centuria;

/**
 * Created by Martin Eckardt on 27.11.2015.
 */
public interface CenturiaValidator {
    void validateCenturia(Centuria centuria) throws ValidationException;

    void validateYear(Integer year) throws ValidationException;
}
