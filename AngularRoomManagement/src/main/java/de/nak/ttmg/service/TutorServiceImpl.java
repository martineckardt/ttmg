package de.nak.ttmg.service;

import de.nak.ttmg.dao.TutorDAO;
import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.util.DateRangeValidator;
import de.nak.ttmg.util.TutorValidator;
import de.nak.ttmg.util.ValidationException;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 */
public class TutorServiceImpl implements TutorService {

    private TutorDAO tutorDAO;
    private TutorValidator tutorValidator = new TutorValidator();

    @Override
    public Long createTutor(Tutor tutor) throws ValidationException {
        tutorValidator.validateTutor(tutor);
        return tutorDAO.create(tutor);
    }

    @Override
    public List<Tutor> listTutors(Date freeStart, Date freeEnd)  throws ValidationException{
        DateRangeValidator.validateDateRange(freeStart,freeEnd);
        //TODO
        return tutorDAO.findAll();
    }

    @Override
    public Tutor loadTutor(Long id) throws ValidationException {
       return tutorDAO.load(id);
    }

    @Inject
    public void setTutorDAO(TutorDAO tutorDAO) {
        this.tutorDAO = tutorDAO;
    }
}
