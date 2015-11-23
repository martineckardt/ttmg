package de.nak.ttmg.controller;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;
import de.nak.ttmg.pdf.PDFCreator;
import de.nak.ttmg.service.CenturiaService;
import de.nak.ttmg.exceptions.ValidationException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This RestController handles all requests regarding centurias.
 */
@RestController
public class CenturiaController {

    @Inject
    private CenturiaService centuriaService;

    /**
     * Requests all centuria
     * @param year the year to be filtered to (YYYY) (optional)
     * @param studyProgram the program to be filtered to (optinal)
     * @return List with Centurias
     */
    @RequestMapping(value = "/centurias", method = RequestMethod.GET)
    public List<Centuria> listCenturias(@RequestParam(required = false, value = "year") Integer year,
                                    @RequestParam(required = false, value = "program") String studyProgram) {
        StudyProgram program = StudyProgram.programForString(studyProgram);
        return centuriaService.listCenturias(year, program);
    }

    /**
     * Requests a specified centuria with a given id
     * @param id of the centuria
     * @return centuria
     */
    @RequestMapping(value = "/centurias/{id}", method = RequestMethod.GET)
    public Centuria getCenturia(@PathVariable Long id) {
        return centuriaService.loadCenturia(id);
    }

    /**
     * Creates a new centuria
     * @param centuria to be created
     * @return created centuria with id from db
     */
    @RequestMapping(value = "/centurias", method = RequestMethod.POST)
    public Centuria createCenturia(@RequestBody Centuria centuria) {
        return centuriaService.createCenturia(centuria);
    }

    /**
     * Creates a PDF time table for a given centuria
     * @param id of the centuria to create the time table for
     * @return PDF File
     */
    @RequestMapping(value = "/centurias/{id}/schedule.pdf", method = RequestMethod.GET, produces = "application/pdf")
    public InputStreamResource getTimeTablePDF(@PathVariable final Long id) {
        try {
            Centuria centuria;
            try {
                centuria = getCenturia(id);
            } catch (ValidationException e) {
                centuria = null;
            }
            return PDFCreator.createPDF(centuria, "Centuria", id);
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream: " + ex.getMessage());
        }
    }

    /**
     * Deletes a centuria.
     * Note: A centuria can only be deleted if it does not have any courses
     * @param id of the centuria to be created
     * @param force if true, the centuria will be deleted even if it has courses
     */
    @RequestMapping(value = "/centurias/{id}", method = RequestMethod.DELETE)
    public void deleteCenturias(@PathVariable Long id,
                                @RequestParam(required = false, value = "force") Boolean force) {
        centuriaService.deleteCenturia(id, force);
    }
}
