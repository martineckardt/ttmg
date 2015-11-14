/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('listCenturiasController', ['$scope', 'CenturyFactory', function ($scope, CenturyFactory) {

    console.log('listCenturiasController started');

    // Setup scope model
    $scope.model = {
        centurias: []
    };

    // Load centurias from REST API
    CenturyFactory.query(function successCallback(data) {
        // Logging
        console.log("Successfully queried entities");
        console.log(data);

        // Set model
        $scope.model.centurias = data;
    }, function errorCallback(error) {
        // Logging
        console.log("Error loading entities:");
        console.log(error);

        // TODO Error handling
    });
}]);