/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.provider').service('courseService', ['$http', function ($http) {

    console.log('courseService initialized')

    /**
     * Return all courses using an asynchronous REST call with promise.
     * @returns {HttpPromise}.
     */
    this.listCoursesWithPromise = function () {
        return $http.get('rest/courses');
    };

    /**
     * Returns the course witht he given id using an asynchronous REST call with promise.
     * @param courseId the id of the requested course
     * @returns {HttpPromise}.
     */
    this.getCourseWithPromise = function (courseId) {
        return $http.get('rest/courses/' + courseId);
    };

    /**
     * Saves a given course using an asynchronous REST call with promise.
     * @param course The course to be saved.
     * @returns {HttpPromise}.
     */
    this.saveCourseWithPromise = function (course) {
        return $http.put('rest/courses', course);
    };

    /**
     * Deletes the given course using an asynchronous REST call with promise.
     * @param course The course to be deleted.
     * @returns {HttpPromise}.
     */
    this.deleteCourseWithPromise = function (course) {
        return $http.delete('rest/courses/' + course.id);
    }

}]);