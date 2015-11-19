/**
 * Created by Martin Eckardt on 28.10.2015.
 * Factory for querying a specific entitiy, all entities and creating a new entity of the type course.
 */

angular.module('ttmg.provider').factory('CourseFactory', function ($resource) {
    return $resource('rest/courses/:courseId', {}, {
        query: {method: 'GET', isArray: true},
        create: {method: 'POST'},
        update: {method: 'PUT'},
        delete: {method: 'DELETE'},
        getEvents: {method: 'GET', isArray: true, url: 'rest/courses/:courseId/events'}
    })
});
