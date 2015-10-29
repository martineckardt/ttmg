package de.nak.ttmg.controller;

import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.service.TutorService;
import de.nak.ttmg.util.ServerResponse;
import de.nak.ttmg.util.ValidationException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by felixb on 28/10/15.
 */
@RestController
public class TutorController {
    private TutorService tutorService;

    @RequestMapping(value = "/tutors", method = RequestMethod.GET)
    public ServerResponse<List<Tutor>> listTutors(@RequestParam(required = false, value = "freeStart") Date freeStart,
                                  @RequestParam(required = false, value = "freeEnd") Date freeEnd
                                 ) {
        try {
            return new ServerResponse<>(tutorService.listTutors(freeStart, freeEnd));
        } catch (ValidationException e) {
            return new ServerResponse<>(e);
        }
    }

    @RequestMapping(value = "/tutors/{id}",method = RequestMethod.GET)
    public ServerResponse<Tutor> getTutor(@PathVariable Long id) {
        try {
            return new ServerResponse<>(tutorService.loadTutor(id));
        } catch (ValidationException e) {
            return new ServerResponse<>(e);
        }
    }

    @RequestMapping(value = "/tutors", method = RequestMethod.POST)
    public ServerResponse<Long> createTutor(@RequestBody Tutor tutor) {
        try {
            return new ServerResponse<>(tutorService.createTutor(tutor));
        } catch (ValidationException e) {
            return new ServerResponse<>(e);
        }
    }

    @Inject
    public void setTutorService(TutorService tutorService) {
        this.tutorService = tutorService;
    }
}

