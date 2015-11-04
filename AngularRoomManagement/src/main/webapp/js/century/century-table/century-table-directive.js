/**
 * Created by U519643 on 03.11.2015.
 * Directive for displaying a set of centurias in a table.
 */

angular.module('ttmg').directive('centuryTable', function () {
    return {
        restrict: 'E',
        scope: {
            centurias: '=',
            hideDownloadButton: '='
        },
        templateUrl: 'js/century/century-table/century-table.html'
    };
});