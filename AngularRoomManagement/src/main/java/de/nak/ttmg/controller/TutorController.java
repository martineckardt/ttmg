package de.nak.ttmg.controller;

import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.pdf.PDFCreator;
import de.nak.ttmg.service.TutorService;
import de.nak.ttmg.exceptions.ValidationException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This RestController handles all requests regarding tutors.
 */
@RestController
public class TutorController {

    @Inject
    private TutorService tutorService;

    /**
     * Requests all tutors
     * @return list of tutors
     */
    @RequestMapping(value = "/tutors", method = RequestMethod.GET)
    public List<Tutor> listTutors() {
        return tutorService.listTutors();
    }

    /**
     * Requests a single tutor
     * @param id of the tutor
     * @return tutor
     */
    @RequestMapping(value = "/tutors/{id}",method = RequestMethod.GET)
    public Tutor getTutor(@PathVariable Long id) {
        return tutorService.loadTutor(id);
    }

    /**
     * Creates a PDF time table for a given tutor
     * @param id of the tutor to create the time table for
     * @return PDF File
     */
    @RequestMapping(value = "/tutors/{id}/schedule.pdf", method = RequestMethod.GET, produces = "application/pdf")
    public InputStreamResource getTimeTablePDF(@PathVariable final Long id) {
        try {
            Tutor tutor;
            try {
                tutor = getTutor(id);
            } catch (ValidationException e) {
                tutor = null;
            }
            return PDFCreator.createPDF(tutor, "Tutor", id);
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream: " + ex.getMessage());
        }
    }

    /**
     * Creates a new tutor
     * @param tutor parameters
     * @return new tutor with id from db
     */
    @RequestMapping(value = "/tutors", method = RequestMethod.POST)
    public Tutor createTutor(@RequestBody Tutor tutor) {
        return tutorService.createTutor(tutor);
    }

    /**
     * Deletes a tutor
     * @param id of the tutor to be deleted
     * @param force if true, validation will be disabled (and all courses with this tutor will be removed)
     */
    @RequestMapping(value = "/tutors/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable Long id, @RequestParam(required = false, value = "force") Boolean force) {
        tutorService.deleteTutor(id, force == null ? false : force);
    }
}

