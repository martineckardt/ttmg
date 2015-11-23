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

    @Inject
    private CourseService courseService;

    /**
     * Requests all courses
     * @return list of courses
     */
    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public List<Course> listCourses() {
        return courseService.listCourses();
    }

    /**
     * Requests a specific course
     * @param id of the course to be returned
     * @return course
     */
    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    public Course getCourse(@PathVariable Long id) {
        return courseService.loadCourse(id);
    }

    /**
     * Creates a new course
     * @param course to be created
     * @param force if true time and capacity validation will be disabled (optional)
     * @return created course with id from db
     */
    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public Course createCourse(@RequestBody Course course,
                            @RequestParam(required = false, value = "force") Boolean force) {
        return courseService.createCourse(course, force == null ? false : force);
    }

    /**
     * Updates an existing course
     * @param id of the course to be updated
     * @param course object with new parameters
     * @param force if true time and capacity validation will be disabled (optional)
     * @return updated course
     */
    @RequestMapping(value = "/courses/{id}", method = RequestMethod.PUT)
    public Course saveCourse(@PathVariable Long id,
                          @RequestBody Course course,
                          @RequestParam(required = false, value = "force") Boolean force) {
        return courseService.updateCourse(id, course, force == null ? false : force);
    }

    /**
     * Deletes a given course and all events
     * @param id of the course to be deleted
     */
    @RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
