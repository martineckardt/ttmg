/**
 * Created by Martin Eckardt on 03.11.2015.
 * Directive for displaying a set of centurias in a table with links to the detail view of the centuria and a the pdf
 * file of the time table.
 */

angular.module('ttmg').directive('centuriaTable', function () {
    return {
        restrict: 'E',
        scope: {
            centurias: '=',
            // Set true to not show the "Time table" column
            hideButtons: '='
        },
        templateUrl: 'js/centuria/centuria-table/centuria-table-template.html'
    };
});