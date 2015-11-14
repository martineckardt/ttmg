/**
 * Created by Martin Eckardt on 28.10.2015.
 * Factory for querying a specific entitiy, all entities and creating a new entity of the type room.
 */

angular.module('ttmg.provider').factory('RoomFactory', function ($resource) {
    return $resource('rest/rooms/:id', {}, {
        query: {method: 'GET', isArray: true},
        create: {method: 'POST'}
    })
});
