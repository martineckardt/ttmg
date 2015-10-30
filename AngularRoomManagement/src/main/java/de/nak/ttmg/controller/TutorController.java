package de.nak.ttmg.controller;

import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.pdf.TimeTableCreator;
import de.nak.ttmg.service.TutorService;
import de.nak.ttmg.util.ValidationException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This RestController handles all requests regarding tutors.
 */
@RestController
public class TutorController {
    private TutorService tutorService;

    @RequestMapping(value = "/tutors", method = RequestMethod.GET)
    public List<Tutor> listTutors(@RequestParam(required = false, value = "freeStart") Date freeStart,
                                  @RequestParam(required = false, value = "freeEnd") Date freeEnd
                                 ) {
        return tutorService.listTutors(freeStart, freeEnd);
    }

    @RequestMapping(value = "/tutors/{id}",method = RequestMethod.GET)
    public Tutor getTutor(@PathVariable Long id) {
        return tutorService.loadTutor(id);
    }

    @RequestMapping(value = "/tutors/{id}/pdf", method = RequestMethod.GET, produces="application/pdf")
    public InputStreamResource getTimeTablePDF(@PathVariable final Long id) {
        try {
            Tutor tutor;
            try {
                tutor = getTutor(id);
            } catch (ValidationException e) {
                tutor = null;
            }
            return TimeTableCreator.createPDF(tutor, "Tutor", id);
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream: " + ex.getMessage());
        }
    }

    @RequestMapping(value = "/tutors", method = RequestMethod.POST)
    public Long createTutor(@RequestBody Tutor tutor) {
        return tutorService.createTutor(tutor);
    }

    @RequestMapping(value = "/tutors/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable Long id, @RequestParam(required = false, value = "force") Boolean force) {
        tutorService.deleteTutor(id, force);
    }

    @Inject
    public void setTutorService(TutorService tutorService) {
        this.tutorService = tutorService;
    }
}

