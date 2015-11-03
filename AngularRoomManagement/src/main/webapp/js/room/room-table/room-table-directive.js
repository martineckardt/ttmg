/**
 * Created by U519643 on 03.11.2015.
 * Directive for displaying a set of rooms in a table.
 */

angular.module('ttmg').directive('roomTable', function () {
    return {
        restrict: 'E',
        scope: {rooms: '='},
        templateUrl: 'js/room/room-table/room-table.html'
    };
});