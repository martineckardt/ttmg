/**
 * Created by Martin Eckardt on 28.10.2015.
 *
 */

angular.module('ttmg.controllers').controller('viewRoomController', ['$scope', '$routeParams', 'RoomFactory', 'EventResourceFactory', function ($scope, $routeParams, RoomFactory, EventResourceFactory) {

    // Route parameters
    var roomId = $routeParams.id;
    console.log('viewRoomController for Room ' + roomId + ' started');

    // Set up scope model
    $scope.model = {
        room: RoomFactory.get({roomId: roomId}),
        events: EventResourceFactory.query({roomId: roomId})
    };
}]);