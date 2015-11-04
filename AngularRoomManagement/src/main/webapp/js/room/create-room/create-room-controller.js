/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('createRoomController', ['$scope', 'RoomsFactory', 'roomService', function ($scope, RoomsFactory, roomService) {
    console.log('createRoomController initialized');

    //create new room instance
    $scope.room = new RoomsFactory();

    //create a new room. Issues a POST to /rest/rooms
    $scope.addRoom = function () {
        console.log("attempting to add room");
        console.log($scope.room);
        $scope.room.$create(function (response) {
                console.log("Succesfully saved room");
                console.log(response);
            }, function (error) {
                console.log("error adding new room");
                console.log(error);
            }
        );
    };

}]);