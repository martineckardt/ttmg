/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('listCenturiasController', ['$scope', 'centuryService', function ($scope, centuryService) {

    console.log('listCenturiasController started');

    // Setup scope model
    $scope.model = {
        centurias: []
    };

    // Load rooms from REST API
    centuryService.listCenturiasWithPromise().then(function successCallback(response) {
        $scope.model.centurias = response.data;
    }, function errorCallback(response) {
        console.log('failed to query centruias');
    });
}]);