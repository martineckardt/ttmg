/**
 * Created by Martin Eckardt on 28.10.2015.
 * Factory for querying a specific entitiy, all entities and creating a new entity of the type course.
 */

angular.module('ttmg.provider').factory('CourseFactory', function ($resource) {
    return $resource('rest/courses/:id', {}, {
        query: {method: 'GET', isArray: true},
        create: {method: 'POST'},
        delete: {method: 'DELETE'}
    })
});
