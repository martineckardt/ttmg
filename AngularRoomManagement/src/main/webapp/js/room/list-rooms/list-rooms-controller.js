/**
 * Created by Martin Eckardt on 28.10.2015.
 * Controller to list all rooms.
 */

angular.module('ttmg.controllers').controller('listRoomsController', ['$scope', 'RoomFactory', function ($scope, RoomFactory) {

    console.log('listRoomsController started');

    // Setup scope model
    $scope.model = [];

    // Load rooms
    RoomFactory.query(function successCallback(data) {
        // Logging
        console.log("Successfully queried entities");
        console.log(data);

        // Set model
        $scope.model.rooms = data;
    }, function errorCallback(error) {
        // Logging
        console.log("Error loading entities:");
        console.log(error);

        // TODO Error handling
    });
}]);