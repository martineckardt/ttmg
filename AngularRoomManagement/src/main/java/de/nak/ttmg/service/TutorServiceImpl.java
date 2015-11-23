package de.nak.ttmg.service;

import de.nak.ttmg.dao.TutorDAO;
import de.nak.ttmg.exceptions.EntityNotFoundException;
import de.nak.ttmg.exceptions.IsBusyException;
import de.nak.ttmg.exceptions.ValidationException;
import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.validator.TutorValidator;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 * The Service Implementation for Tutors
 */
public class TutorServiceImpl implements TutorService {

    @Inject
    private TutorDAO tutorDAO;

    private final TutorValidator tutorValidator = new TutorValidator();

    @Override
    public Tutor createTutor(Tutor tutor) throws ValidationException {
        tutor.setTitle(tutor.getTitle().trim());
        tutor.setLastName(tutor.getLastName().trim());
        tutor.setFirstName(tutor.getFirstName().trim());
        tutorValidator.validateTutor(tutor);
        return tutorDAO.create(tutor);
    }

    @Override
    public List<Tutor> listTutors()  throws ValidationException{
        return tutorDAO.findAll();
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
