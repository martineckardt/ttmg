package de.nak.ttmg.service;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;
import de.nak.ttmg.exceptions.ValidationException;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This is the interface for the Centuria Service
 */
public interface CenturiaService {

    Centuria createCenturia(Centuria centuria) throws ValidationException;

    List<Centuria> listCenturias(Integer year, StudyProgram program) throws ValidationException;

    Centuria loadCenturia(Long id) throws ValidationException;

    void deleteCenturia(Long id, boolean force) throws ValidationException;
}
