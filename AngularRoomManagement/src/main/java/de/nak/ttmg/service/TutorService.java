package de.nak.ttmg.service;

import de.nak.ttmg.model.Tutor;

import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public interface TutorService {

    Long createTutor(Tutor tutor);

    List<Tutor> listTutors(Date freeStart, Date freeEnd);

    Tutor loadTutor(Long id);
}
