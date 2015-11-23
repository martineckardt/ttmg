package de.nak.ttmg.service;

import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.exceptions.ValidationException;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This is the interface for the Tutor Service
 */
public interface TutorService {

    Tutor createTutor(Tutor tutor) throws ValidationException;

    List<Tutor> listTutors() throws ValidationException;

    Tutor loadTutor(Long id) throws ValidationException;

    void deleteTutor(Long id, boolean force) throws ValidationException;
}
