package de.nak.ttmg.controller;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;
import de.nak.ttmg.pdf.TimeTableCreator;
import de.nak.ttmg.service.CenturiaService;
import de.nak.ttmg.util.ValidationException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
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
        return centuriaService.listCenturias(year, program);
    }

    @RequestMapping(value = "/centurias/{id}", method = RequestMethod.GET)
    public Centuria getCenturia(@PathVariable Long id) {
        return centuriaService.loadCenturia(id);
    }

    @RequestMapping(value = "/centurias", method = RequestMethod.POST)
    public Long createCenturia(@RequestBody Centuria centuria) {
        return centuriaService.createCenturia(centuria);
    }

    @RequestMapping(value = "/centurias/{id}/pdf", method = RequestMethod.GET, produces="application/pdf")
    public InputStreamResource getTimeTablePDF(@PathVariable final Long id) {
        try {
            Centuria centuria;
            try {
                centuria = getCenturia(id);
            } catch (ValidationException e) {
                centuria = null;
            }
            return TimeTableCreator.createPDF(centuria, id);
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @Inject
    public void setRoomService(CenturiaService centuriaService) {
        this.centuriaService = centuriaService;
    }
}
