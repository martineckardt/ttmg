package de.nak.ttmg.controller;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;
import de.nak.ttmg.service.CenturiaService;
import de.nak.ttmg.util.ServerResponse;
import de.nak.ttmg.util.ValidationException;
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
    public ServerResponse<List<Centuria>> listRooms(@RequestParam(required = false, value = "year") Integer year,
                                    @RequestParam(required = false, value = "program") String studyProgram) {
        StudyProgram program = StudyProgram.programForString(studyProgram);
        return new ServerResponse<>(()->centuriaService.listCenturias(year, program));
    }

    @RequestMapping(value = "/centurias/{id}", method = RequestMethod.GET)
    public ServerResponse<Centuria> getRoom(@PathVariable Long id) {
        return new ServerResponse<>(()->centuriaService.loadCenturia(id));
    }

    @RequestMapping(value = "/centurias", method = RequestMethod.POST)
    public ServerResponse<Long> createCenturia(@RequestBody Centuria centuria) {
        return new ServerResponse<>(()-> centuriaService.createCenturia(centuria));
    }

    @Inject
    public void setRoomService(CenturiaService centuriaService) {
        this.centuriaService = centuriaService;
    }
}
