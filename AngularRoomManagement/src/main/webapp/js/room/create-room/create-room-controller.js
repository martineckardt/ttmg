/**
 * Created by Martin Eckardt on 28.10.2015.
 * Controller for creation of a room.
 */

angular.module('ttmg.controllers').controller('createRoomController', ['$scope', 'RoomFactory', 'BUIDLINGS', 'ROOM_TYPES', function ($scope, RoomFactory, BUIDLINGS, ROOM_TYPES) {
    console.log('createRoomController initialized');

    // Set up form model
    $scope.model = {
        room: new RoomFactory(),
        buildings: BUIDLINGS,
        roomTypes: ROOM_TYPES
    };

    //create a new room
    $scope.addRoom = function () {
        $scope.model.room.$create(
            function successCallback(data) {
                // Logging
                console.log("entity sucessfully created");
                console.log(data);

                // Fill messageData with newly created entity
                $scope.entitySuccesfullyCreated = true;
                $scope.messageData = data;

                // Reset form model
                $scope.room = new RoomFactory();
            }, function errorCallback(error) {
                // Logging
                console.log("Error creating entity");
                console.log(error);

                // Fill messageData with exception message from backend
                $scope.entitySuccesfullyCreated = false;
                $scope.messageData = error.data.message;
            }
        );
    };

}]);