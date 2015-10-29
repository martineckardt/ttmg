package de.nak.ttmg.service;

import de.nak.ttmg.dao.TutorDAO;
import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.util.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 */
public class TutorServiceImpl implements TutorService {

    private TutorDAO tutorDAO;
    private final TutorValidator tutorValidator = new TutorValidator();
    private final TimeValidator timeValidator = new TimeValidator();

    @Override
    public Long createTutor(Tutor tutor) throws ValidationException {
        tutorValidator.validateTutor(tutor);
        return tutorDAO.create(tutor);
    }

    @Override
    public List<Tutor> listTutors(Date freeStart, Date freeEnd)  throws ValidationException{
        DateRangeValidator.validateDateRange(freeStart,freeEnd);
        List<Tutor> allTutors = tutorDAO.findAll();
        if (freeStart != null) {
            allTutors.stream().filter(tutor -> timeValidator.hasTime(tutor, freeStart, freeEnd));
        }
        return allTutors;
    }

    @Override
    public Tutor loadTutor(Long id) throws ValidationException {
        Tutor tutor = tutorDAO.load(id);
        if (tutor == null) {
            throw new EntityNotFoundException("tutor", id);
        }
       return tutor;
    }

    @Inject
    public void setTutorDAO(TutorDAO tutorDAO) {
        this.tutorDAO = tutorDAO;
    }
}
