/**
 * Created by U519643 on 03.11.2015.
 */
angular.module('ttmg').directive('centuryTable', function () {
    return {
        restrict: 'E',
        scope: {centurias: '='},
        templateUrl: 'js/century/century-table/century-table.html'
    };
});