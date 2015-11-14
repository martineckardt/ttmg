/**
 * Created by Martin Eckardt on 28.10.2015.
 * Factory for querying a specific entitiy, all entities and creating a new entity of the type tutor.
 */

angular.module('ttmg.provider').factory('TutorFactory', function ($resource) {
    return $resource('rest/tutors/:id', {}, {
        query: {method: 'GET', isArray: true},
        create: {method: 'POST'}
    })
});
