/**
 * Created by U519643 on 28.10.2015.
 * Factory for querying all rooms and creating new rooms.
 */

angular.module('ttmg.services').factory('RoomsFactory', function ($resource) {
    return $resource('rest/rooms', {}, {
        query: {method: 'GET', isArray: true},
        create: {method: 'POST'}
    })
});
