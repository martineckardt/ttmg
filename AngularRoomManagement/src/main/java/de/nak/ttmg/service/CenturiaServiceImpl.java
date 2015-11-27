package de.nak.ttmg.service;

import de.nak.ttmg.dao.CenturiaDAO;
import de.nak.ttmg.exceptions.*;
import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;
import de.nak.ttmg.validator.CenturiaValidator;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 * The Service Implementation for Centurias
 */
public class CenturiaServiceImpl implements CenturiaService {

    @Inject
    private CenturiaDAO centuriaDAO;

    @Inject
    private CenturiaValidator centuriaValidator;

    @Override
    public Centuria createCenturia(Centuria centuria) throws ValidationException {
        if (centuria.getLetter() != null) {
            //We always want a small letter
            centuria.setLetter(Character.toLowerCase(centuria.getLetter()));
        }
        //Validate if the centuria is ok
        centuriaValidator.validateCenturia(centuria);
        if (centuria.getId() == null) {
            try {
                //Create the centuria in the backend
                return centuriaDAO.create(centuria);
            } catch (Exception e) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    throw new EntityAlreadyExistsException();
                }
                throw new ValidationException(e.getCause());
            }
        } else {
            throw new InvalidParameterException("centuriaId", InvalidParameterException.InvalidParameterType.INVALID_NOT_NULL);
        }
    }

    @Override
    public List<Centuria> listCenturias(Integer year, StudyProgram program) {
        if (year != null) {
            //Validate the year, if set
            centuriaValidator.validateYear(year);
        }
        //Find all centurias
        return centuriaDAO.findAll(year, program);
    }

    @Override
    public Centuria loadCenturia(Long id) {
        return centuriaDAO.load(id);
    }
    @Override
    public void deleteCenturia(Long id, boolean force) throws ValidationException {
        Centuria centuria = loadCenturia(id);
        if (centuria == null) {
            throw new EntityNotFoundException("centuria", id);
        }
        //Check, if the centuria has some courses
        if (!force && centuria.getCourses().size() > 0) {
            throw new IsBusyException(centuria, centuria.getCourses().size());
        }
        //Delete centuria from DB
        centuriaDAO.delete(centuria);
    }
}
