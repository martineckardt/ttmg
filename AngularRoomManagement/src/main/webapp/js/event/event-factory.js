/**
 * Created by Martin Eckardt on 14.11.2015.
 * Factory for querying events
 */


angular.module('ttmg.provider').factory('EventFactory', function ($resource) {
    return $resource('rest/courses/:courseId/events', {}, {
        bulkCreate: {method: 'POST', isArray: true}
    })
});
