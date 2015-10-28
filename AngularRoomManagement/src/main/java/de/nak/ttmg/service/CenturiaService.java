package de.nak.ttmg.service;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public interface CenturiaService {

    Long createCenturia(Centuria centuria);

    List<Centuria> listCenturias(Integer year, StudyProgram program);

    Centuria loadCenturia(Long id);
}
