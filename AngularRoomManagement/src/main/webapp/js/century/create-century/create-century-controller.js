/**
 * Created by Martin Eckardt on 28.10.2015.
 * Controller for creation of entities
 */

angular.module('ttmg.controllers').controller('createCenturyController', ['$scope', 'CenturyFactory', function ($scope, CenturyFactory) {
    console.log('createCenturyController initialized');

    // Set up form model
    $scope.century = new CenturyFactory();

    //create a new century
    $scope.addCentury = function () {
        $scope.century.$create(
            function successCallback(data) {
                // Logging
                console.log("entity sucessfully created");
                console.log(data);

                // Fill messageData with newly created entity
                $scope.entitySuccesfullyCreated = true;
                $scope.messageData = data;

                // Reset form model
                $scope.century = new CenturyFactory();
            }, function errorCallback(error) {
                // Logging
                console.log("Error creating entity");
                console.log(error);

                // Fill messageData with exception messagefrom backend
                $scope.entitySuccesfullyCreated = false;
                $scope.messageData = error.data.message;
            }
        );
    };

}]);