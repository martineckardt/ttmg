/**
 * Created by U519643 on 28.10.2015.
 */

application.controller('listRoomsController', ['$scope', 'roomService', function ($scope, roomService) {

    console.log('listRoomsController started')

    // Setup scope model
    $scope.model = {
        rooms: []
    };

    // Load rooms from REST API
    roomService.listRoomsWithPromise().then(function successCallback(response) {
        $scope.model.rooms = response.data;
    }, function errorCallback(response) {
        alert('Error occured while loading');
    });
}]);