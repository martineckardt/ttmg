package de.nak.ttmg.service;

import de.nak.ttmg.dao.CenturiaDAO;
import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;
import de.nak.ttmg.util.*;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 */
public class CenturiaServiceImpl implements CenturiaService {

    @Inject
    private CenturiaDAO centuriaDAO;
    private final CenturiaValidator centuriaValidator = new CenturiaValidator();

    @Override
    public Centuria createCenturia(Centuria centuria) throws ValidationException {
        centuriaValidator.validateCenturia(centuria);
        if (centuria.getId() == null) {
            try {
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
            centuriaValidator.validateYear(year);
        }
        return centuriaDAO.findAll(year, program);
    }

    @Override
    public Centuria loadCenturia(Long id) {
        Centuria centuria = centuriaDAO.load(id);
        if (centuria == null) {
            throw new EntityNotFoundException("centuria", id);
        }
        return centuria;
    }
    @Override
    public void deleteCenturia(Long id, Boolean force) throws ValidationException {
        Centuria centuria = loadCenturia(id);
        if (centuria == null) {
            throw new EntityNotFoundException("centuria", id);
        }
        if ((force == null || !force) && centuria.getCourses().size() > 0) {
            throw new IsBusyException(centuria);
        }
        centuriaDAO.delete(centuria);
    }
}
