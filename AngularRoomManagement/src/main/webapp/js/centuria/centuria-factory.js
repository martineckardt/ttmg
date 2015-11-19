/**
 * Created by Martin Eckardt on 28.10.2015.
 * Factory for querying a specific entitiy, all entities and creating a new entity of the type centuria.
 */

angular.module('ttmg.provider').factory('CenturiaFactory', function ($resource) {
    return $resource('rest/centurias/:centuriaId', {}, {
        query: {method: 'GET', isArray: true},
        create: {method: 'POST'},
        getEvents: {method: 'GET', isArray: true, url: 'rest/rooms/:centuriaId/events'}
    })
});
