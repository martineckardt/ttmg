package de.nak.ttmg.service;

import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.util.ValidationException;

import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This is the interface for the Tutor Service
 */
public interface TutorService {

    Long createTutor(Tutor tutor) throws ValidationException;

    List<Tutor> listTutors(Date freeStart, Date freeEnd) throws ValidationException;

    Tutor loadTutor(Long id) throws ValidationException;
}
