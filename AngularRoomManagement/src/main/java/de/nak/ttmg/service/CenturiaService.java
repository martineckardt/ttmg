package de.nak.ttmg.service;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;
import de.nak.ttmg.util.ValidationException;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public interface CenturiaService {

    Long createCenturia(Centuria centuria) throws ValidationException;

    List<Centuria> listCenturias(Integer year, StudyProgram program) throws ValidationException;

    Centuria loadCenturia(Long id) throws ValidationException;
}
