/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('createRoomController', ['$scope', 'RoomsFactory', function ($scope, RoomsFactory) {
    console.log('createRoomController initialized');

    //create new room instance
    $scope.room = new RoomsFactory();

    //create a new room. Issues a POST to /rest/rooms
    $scope.addRoom = function () {
        $scope.room.$create(function (data) {
                console.log(data);
                $scope.roomSuccesfullyCreated = true;
                $scope.messageData = data;
                $scope.room = new RoomsFactory();
            }, function (error) {
                $scope.roomSuccesfullyCreated = false;
                console.log(error);
                $scope.messageData = error.data.message;
            }
        );
    };

}]);