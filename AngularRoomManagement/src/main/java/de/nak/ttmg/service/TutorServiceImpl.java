package de.nak.ttmg.service;

import de.nak.ttmg.dao.TutorDAO;
import de.nak.ttmg.model.Tutor;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 */
public class TutorServiceImpl implements TutorService {

    private TutorDAO tutorDAO;
    @Override
    public void createTutor(Tutor tutor) {
        tutorDAO.create(tutor);
    }

    @Override
    public List<Tutor> listTutors() {
        return tutorDAO.findAll();
    }

    @Override
    public Tutor loadTutor(Long id) {
       return tutorDAO.load(id);
    }

    @Inject
    public void setTutorDAO(TutorDAO tutorDAO) {
        this.tutorDAO = tutorDAO;
    }
}
