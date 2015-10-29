package de.nak.ttmg.controller;

import de.nak.ttmg.model.Course;
import de.nak.ttmg.model.Event;
import de.nak.ttmg.service.CourseService;
import de.nak.ttmg.util.InvalidParameterException;
import de.nak.ttmg.util.ServerResponse;
import de.nak.ttmg.util.ValidationException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by felixb on 27/10/15.
 */
@RestController
public class CourseController {

    private CourseService courseService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public List<Course> listCourses(@RequestParam(required = false, value = "centuriaId") Long centuriaId,
                                  @RequestParam(required = false, value = "tutorId") Long tutorId,
                                  @RequestParam(required = false, value = "roomId") Long roomId,
                                  @RequestParam(required = false, value = "rangeStart") Date rangeStart,
                                  @RequestParam(required = false, value = "rangeEnd") Date rangeEnd) {

        return courseService.listCourses();
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    public ServerResponse<Course> getCourse(@PathVariable Long id) {
        try {
            Course course = courseService.loadCourse(id);
            return new ServerResponse<>(course);
        } catch (ValidationException e) {
            return new ServerResponse<>(e);
        }
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public ServerResponse<Long> createCourse(@RequestBody Course course,
                            @RequestParam(required = false, value = "force") Boolean force) {
        try {
            Long result = courseService.createCourse(course, force);
            return new ServerResponse<>(result);
        } catch (ValidationException e) {
            return new ServerResponse<>(e);
        }
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.PUT)
    public ServerResponse<Boolean> saveCourse(@PathVariable Long id,
                          @RequestBody Course course,
                          @RequestParam(required = false, value = "force") Boolean force) {
        if (course != null && course.getId() != null && course.getId().equals(id)) {
            try {
                courseService.updateCourse(course, force);
                return new ServerResponse<>(true);
            } catch (ValidationException e) {
                return new ServerResponse<>(e);
            }
        }
        return new ServerResponse<>(new InvalidParameterException("courseId", InvalidParameterException.InvalidParameterType.INCONSISTENT));
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE)
    public ServerResponse<Boolean> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return new ServerResponse<>(true);
        } catch (ValidationException e) {
            return new ServerResponse<>(e);
        }
    }

    @Inject
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
