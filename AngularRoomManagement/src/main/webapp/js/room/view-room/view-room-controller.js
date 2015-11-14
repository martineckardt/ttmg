/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('viewRoomController', ['$scope', '$routeParams', 'RoomsFactory', function ($scope, $routeParams, RoomsFactory) {

    console.log('viewRoomController for Room ' + $routeParams.id + ' started');

    $scope.model = [];

    $scope.model.room = RoomsFactory.get({id: $routeParams.id});
}]);