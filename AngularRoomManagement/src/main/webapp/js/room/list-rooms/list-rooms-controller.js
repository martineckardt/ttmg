/**
 * Created by U519643 on 28.10.2015.
 * Controller to list all rooms.
 */

angular.module('ttmg.controllers').controller('listRoomsController', ['$scope', 'RoomsFactory', function ($scope, RoomsFactory) {

    console.log('listRoomsController started');

    // Setup scope model
    $scope.model = [];

    // Load rooms from REST API
    RoomsFactory.query(function (data) {
        $scope.model.rooms = data;
    }, function (error) {
        console.log("error:");
        console.log(error);
    });
}]);