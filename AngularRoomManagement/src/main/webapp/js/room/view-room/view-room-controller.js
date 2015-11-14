/**
 * Created by Martin Eckardt on 28.10.2015.
 *
 */

angular.module('ttmg.controllers').controller('viewRoomController', ['$scope', '$routeParams', 'RoomFactory', function ($scope, $routeParams, RoomFactory) {

    console.log('viewRoomController for Room ' + $routeParams.id + ' started');

    $scope.model = [];

    $scope.model.room = RoomFactory.get({id: $routeParams.id});
}]);