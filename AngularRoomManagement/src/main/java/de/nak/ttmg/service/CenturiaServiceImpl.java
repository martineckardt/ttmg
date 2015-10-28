package de.nak.ttmg.service;

import de.nak.ttmg.dao.CenturiaDAO;
import de.nak.ttmg.model.Centuria;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 */
public class CenturiaServiceImpl implements CenturiaService {

    private CenturiaDAO centuriaDAO;

    @Override
    public Long createCenturia(Centuria centuria) {
        return centuriaDAO.create(centuria);
    }

    @Override
    public List<Centuria> listCenturias() {
        return centuriaDAO.findAll();
    }

    @Override
    public Centuria loadCenturia(Long id) {
        return centuriaDAO.load(id);
    }

    @Inject
    public void setCenturiaDAO (CenturiaDAO centuriaDAO) {
        this.centuriaDAO = centuriaDAO;
    }
}
