package de.nak.ttmg.service;

import de.nak.ttmg.dao.TutorDAO;
import de.nak.ttmg.exceptions.EntityNotFoundException;
import de.nak.ttmg.exceptions.IsBusyException;
import de.nak.ttmg.exceptions.ValidationException;
import de.nak.ttmg.model.DateRange;
import de.nak.ttmg.model.DateRangeFactory;
import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.validator.TimeValidator;
import de.nak.ttmg.validator.TutorValidator;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 * The Service Implementation for Tutors
 */
public class TutorServiceImpl implements TutorService {

    @Inject
    private TutorDAO tutorDAO;

    private final TutorValidator tutorValidator = new TutorValidator();
    private final TimeValidator timeValidator = new TimeValidator();

    @Override
    public Tutor createTutor(Tutor tutor) throws ValidationException {
        tutorValidator.validateTutor(tutor);
        return tutorDAO.create(tutor);
    }

    @Override
    public List<Tutor> listTutors(Date freeStart, Date freeEnd)  throws ValidationException{
        DateRange freeRange = DateRangeFactory.createDateRange(freeStart, freeEnd);
        List<Tutor> allTutors = tutorDAO.findAll();
        if (freeRange != null) {
            allTutors.stream().filter(tutor -> timeValidator.hasTime(tutor, freeRange));
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

    @Override
    public void deleteTutor(Long id, Boolean force) throws ValidationException {
        Tutor tutor = tutorDAO.load(id);
        if (tutor == null) {
            throw new EntityNotFoundException("tutor", id);
        }
        if ((force == null || !force) && tutor.getCourses().size() > 0) {
            throw new IsBusyException(tutor, tutor.getCourses().size());
        }
        tutorDAO.delete(tutor);
    }
}
