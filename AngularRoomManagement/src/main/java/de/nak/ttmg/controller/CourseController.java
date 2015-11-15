package de.nak.ttmg.controller;

import de.nak.ttmg.model.Course;
import de.nak.ttmg.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by felixb on 27/10/15.
 * This RestController handles all requests regarding courses.
 */
@RestController
public class CourseController {

    private CourseService courseService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public List<Course> listCourses(@RequestParam(required = false, value = "centuriaId") Long centuriaId,
                                  @RequestParam(required = false, value = "tutorId") Long tutorId
                                  //@RequestParam(required = false, value = "roomId") Long roomId
                                  //@RequestParam(required = false, value = "rangeStart") Date rangeStart,
                                  //@RequestParam(required = false, value = "rangeEnd") Date rangeEnd
                                    ) {
        //TODO
        return courseService.listCourses();
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    public Course getCourse(@PathVariable Long id) {
        return courseService.loadCourse(id);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public Course createCourse(@RequestBody Course course,
                            @RequestParam(required = false, value = "force") Boolean force) {
        return courseService.createCourse(course, force);
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.PUT)
    public Course saveCourse(@PathVariable Long id,
                          @RequestBody Course course,
                          @RequestParam(required = false, value = "force") Boolean force) {
        return courseService.updateCourse(id, course, force);
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @Inject
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
