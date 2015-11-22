/**
 * Created by Martin Eckardt on 14.11.2015.
 * Directive to show rooms as a comma-separated String
 */

angular.module('ttmg').directive('roomEnumeration', function () {
    return {
        restrict: 'E',
        scope: {
            rooms: '='
        },
        templateUrl: 'js/room/room-enumeration/room-enumeration-template.html'
    };
});