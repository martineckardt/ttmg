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
        $scope.model.rooms = data;
    }, function errorCallback(error) {
        console.log("error:");
        console.log(error);
    });
}]);