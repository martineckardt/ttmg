/**
 * Created by Martin Eckardt on 03.11.2015.
 * Directive for displaying a set of rooms in a table.
 */

angular.module('ttmg').directive('roomTable', function () {
    return {
        restrict: 'E',
        scope: {
            rooms: '=',
            filter: '='
        },
        templateUrl: 'js/room/room-table/room-table-template.html'
    };
});