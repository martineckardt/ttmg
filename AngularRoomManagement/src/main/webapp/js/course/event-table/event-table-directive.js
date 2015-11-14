/**
 * Created by U519643 on 03.11.2015.
 * Directive for displaying a set of rooms in a table.
 */

angular.module('ttmg').directive('eventTable', function () {
    return {
        restrict: 'E',
        scope: {events: '='},
        templateUrl: 'js/course/event-table/event-table-template.html'
    };
});