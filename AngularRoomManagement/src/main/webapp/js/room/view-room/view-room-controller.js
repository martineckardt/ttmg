/**
 * Created by Martin Eckardt on 28.10.2015.
 *
 */

angular.module('ttmg.controllers').controller('viewRoomController', ['$scope', '$routeParams', 'RoomFactory', 'EventFactory', function ($scope, $routeParams, RoomFactory, EventFactory) {

    var roomId = $routeParams.id;

    console.log('viewRoomController for Room ' + roomId + ' started');

    $scope.model = [];

    $scope.model.room = RoomFactory.get({id: roomId});

    $scope.model.events = EventFactory.query({roomId: roomId});
}]);