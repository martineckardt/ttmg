package de.nak.ttmg.service;

import de.nak.ttmg.model.Tutor;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public interface TutorService {

    void createTutor(Tutor tutor);

    List<Tutor> listTutors();

    Tutor loadTutor(Long id);
}
