/**
 * Created by Martin Eckardt on 28.10.2015.
 *
 */

angular.module('ttmg.controllers').controller('viewCenturyController', ['$scope', '$routeParams', 'CenturyFactory', function ($scope, $routeParams, CenturyFactory) {

    console.log('viewCenturyController for Century ' + $routeParams.id + ' started');

    $scope.model = [];

    $scope.model.century = CenturyFactory.get({id: $routeParams.id});
}]);