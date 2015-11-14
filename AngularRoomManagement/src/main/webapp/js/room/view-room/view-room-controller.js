/**
 * Created by Martin Eckardt on 28.10.2015.
 *
 */

angular.module('ttmg.controllers').controller('viewRoomController', ['$scope', '$routeParams', 'RoomFactory', 'EventFactory', function ($scope, $routeParams, RoomFactory, EventFactory) {

    // Route parameters
    var roomId = $routeParams.id;
    console.log('viewRoomController for Room ' + roomId + ' started');

    // Set up scope model
    $scope.model = {
        room: RoomFactory.get({id: roomId}),
        events: EventFactory.query({roomId: roomId})
    };
}]);