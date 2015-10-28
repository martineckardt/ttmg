package de.nak.ttmg.controller;

import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.service.TutorService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
@RestController
public class TutorController {
    private TutorService tutorService;

    @RequestMapping(value = "/tutors", method = RequestMethod.GET)
    public List<Tutor> listTutors(@RequestParam(required = false, value = "freeStart") Date freeStart,
                                  @RequestParam(required = false, value = "freeEnd") Date freeEnd
                                 ) {
        return  tutorService.listTutors(freeStart,freeEnd);
    }

    @RequestMapping(value = "/tutors/{id}",method = RequestMethod.GET)
    public Tutor getTutor(@PathVariable Long id) {
        return tutorService.loadTutor(id);
    }

    @RequestMapping(value = "/tutors", method = RequestMethod.POST)
    public Long createTutor(@RequestBody Tutor tutor) {
        return tutorService.createTutor(tutor);
    }

    @Inject
    public void setTutorService(TutorService tutorService) {
        this.tutorService = tutorService;
    }
}
