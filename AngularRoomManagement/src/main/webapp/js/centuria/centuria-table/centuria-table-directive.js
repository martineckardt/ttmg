/**
 * Created by Martin Eckardt on 03.11.2015.
 * Directive for displaying a set of centurias in a table.
 */

angular.module('ttmg').directive('centuriaTable', function () {
    return {
        restrict: 'E',
        scope: {
            centurias: '=',
            hideButtons: '='
        },
        templateUrl: 'js/centuria/centuria-table/centuria-table-template.html'
    };
});