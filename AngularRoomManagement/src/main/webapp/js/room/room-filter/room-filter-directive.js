/**
 * Created by Martin Eckardt on 22.11.2015.
 * A directive for a toolbar to select room properties to filter a list of rooms.
 */

angular.module('ttmg').directive('roomFilter', function () {
    return {
        restrict: 'E',
        scope: {filter: '='},
        controller: ['$scope', 'BUILDINGS', 'ROOM_TYPES', function ($scope, BUILDINGS, ROOM_TYPES) {
            $scope.model = {
                buildings: BUILDINGS,
                roomTypes: ROOM_TYPES
            };
        }],
        templateUrl: 'js/room/room-filter/room-filter-template.html'
    };
});