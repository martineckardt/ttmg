/**
 * Created by Martin Eckardt on 29.11.2015.
 * A directive to view the centurias in a tree an select them by year, by program or as a single one.
 * The promise of the centurias needs to be handed in because angular occasionally hands in the unresolved promise value
 * as undefined. To avoid that the controller waits for the promise to resolve.
 */

angular.module('ttmg').directive('centuriaTree', function () {
    return {
        restrict: 'E',
        scope: {centuriasPromise: '='},
        controller: ['$scope', '$q', function ($scope, $q) {
            // Set up scope model
            $scope.model = {
                centuriaProgramsByYear: {}
            };

            // Wait for centuria promise to resolve
            $q.when($scope.centuriasPromise, function successCallback() {
                // Convenience access to properties
                var centurias = $scope.centuriasPromise.$$state.value;
                var centuriaProgramsByYear = $scope.model.centuriaProgramsByYear;

                // Collect unique programs per year from centurias
                centurias.forEach(function (centuria) {

                    if (centuria.year in centuriaProgramsByYear) { // an entry for the current year exists
                        // Check if no entry for the program exists
                        if (centuriaProgramsByYear[centuria.year].indexOf(centuria.program) == -1) {
                            // Add the program to the year
                            centuriaProgramsByYear[centuria.year].push(centuria.program);
                        }
                    } else { // No entry for the current year exists
                        // Add the program to the year
                        centuriaProgramsByYear[centuria.year] = [centuria.program];
                    }
                });
            });

            // Toggles all centurias of a year
            this.setCenturiasWithYear = function (year, bool) {
                //console.log('Set all with year = ' + year + ' to ' + bool);
                var centurias = $scope.centuriasPromise.$$state.value;

                centurias.forEach(function (centuria) {
                    if (centuria.year == year) {
                        centuria.selected = bool;
                    }
                });


            };

            // Toggles all centurias of a year
            this.setCohort = function (year, program, bool) {
                // console.log('set all with year = ' + year + ' and program = ' + program + ' to ' + bool);
                var centurias = $scope.centuriasPromise.$$state.value;

                centurias.forEach(function (centuria) {
                    if (centuria.year == year && centuria.program == program) {
                        centuria.selected = bool;
                    }
                });
            };
        }],
        templateUrl: 'js/centuria/centuria-tree/centuria-tree-template.html',
        controllerAs: 'centuriaTreeCtrl'
    };
});
