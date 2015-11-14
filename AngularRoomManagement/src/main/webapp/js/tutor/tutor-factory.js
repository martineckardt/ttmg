/**
 * Created by Martin Eckardt on 28.10.2015.
 * Factory for querying all rooms and creating new rooms.
 */

angular.module('ttmg.services').factory('TutorFactory', function ($resource) {
    return $resource('rest/tutors/:id', {}, {
        query: {method: 'GET', isArray: true},
        create: {method: 'POST'}
    })
});
