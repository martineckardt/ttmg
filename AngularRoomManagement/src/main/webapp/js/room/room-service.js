/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg.services').service('roomService', ['$http', function ($http) {

    console.log('roomService initialized')

    /**
     * Return all rooms using an asynchronous REST call with promise.
     * @returns {HttpPromise}.
     */
    this.listRoomsWithPromise = function () {
        return $http.get('rest/rooms');
    };

    /**
     * Saves a given room using an asynchronous REST call with promise.
     * @param room The room to be saved.
     * @returns {HttpPromise}.
     */
    this.saveRoomWithPromise = function (room) {
        return $http.put('rest/rooms', room);
    };

    /**
     * Deletes the given room using an asynchronous REST call with promise.
     * @param room The room to be deleted.
     * @returns {HttpPromise}.
     */
    this.deleteRoomWithPromise = function (room) {
        return $http.delete('rest/rooms/' + room.id);
    }

}]);