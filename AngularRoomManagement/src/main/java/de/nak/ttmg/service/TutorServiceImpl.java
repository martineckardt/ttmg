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
        //Trim the title and name properties
        if (tutor.getTitle() != null) {
            tutor.setTitle(tutor.getTitle().trim());
        }
        if (tutor.getFirstName() != null) {
            tutor.setFirstName(tutor.getFirstName().trim());
        }
        if (tutor.getLastName() != null) {
            tutor.setLastName(tutor.getLastName().trim());
        }
        //Check if the tutor has valid properties
        tutorValidator.validateTutor(tutor);
        //Create the tutor in the backend
        return tutorDAO.create(tutor);
    }

    @Override
    public List<Tutor> listTutors()  throws ValidationException{
        return tutorDAO.findAll();
    }

    @Override
    public Tutor loadTutor(Long id) throws ValidationException {
       return tutorDAO.load(id);
    }

    @Override
    public void deleteTutor(Long id, boolean force) throws ValidationException {
        Tutor tutor = tutorDAO.load(id);
        if (tutor == null) {
            throw new EntityNotFoundException("tutor", id);
        }
        //Check if the tutor has some courses
        if (!force && tutor.getCourses().size() > 0) {
            throw new IsBusyException(tutor, tutor.getCourses().size());
        }
        //Delete the tutor in the DB
        tutorDAO.delete(tutor);
    }
}
