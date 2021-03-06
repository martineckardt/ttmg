/**
 * Created by Martin Eckardt on 14.11.2015.
 * Factory for querying events
 */


angular.module('ttmg.provider').factory('EventResourceFactory', function ($resource) {
    return $resource('rest/events/:eventId', {}, {
        bulkCreate: {method: 'POST', url: 'rest/courses/:courseId/events'},
        delete: {method: 'DELETE'},
        update: {method: 'PUT', url: 'rest/courses/:courseId/events/:eventId'},
    })
});
