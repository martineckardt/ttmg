package de.nak.ttmg.controller;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;
import de.nak.ttmg.service.CenturiaService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
@RestController
public class CenturiaController {

    private CenturiaService centuriaService;

    @RequestMapping(value = "/centurias", method = RequestMethod.GET)
    public List<Centuria> listRooms(@RequestParam(required = false, value = "year") Integer year,
                                    @RequestParam(required = false, value = "program") String studyProgram) {
        StudyProgram program = StudyProgram.programForString(studyProgram);
        return centuriaService.listCenturias(year,program);
    }

    @RequestMapping(value = "/centurias/{id}", method = RequestMethod.GET)
    public Centuria getRoom(@PathVariable Long id) throws Exception {
        return centuriaService.loadCenturia(id);
    }

    @RequestMapping(value = "/centurias", method = RequestMethod.POST)
    public Long createCenturia(@RequestBody Centuria centuria) {
        return centuriaService.createCenturia(centuria);
    }

    @Inject
    public void setRoomService(CenturiaService centuriaService) {
        this.centuriaService = centuriaService;
    }
}
