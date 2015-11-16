/**
 * Created by Martin Eckardt on 28.10.2015.
 * Controller for creation of entities
 */

angular.module('ttmg.controllers').controller('createCenturiaController', ['$scope', 'CenturiaFactory', function ($scope, CenturiaFactory) {
    console.log('createCenturiaController initialized');

    // Set up form model
    $scope.centuria = new CenturiaFactory();

    //create a new centuria
    $scope.addCenturia = function () {
        $scope.centuria.$create(
            function successCallback(data) {
                // Logging
                console.log("entity sucessfully created");
                console.log(data);

                // Fill messageData with newly created entity
                $scope.entitySuccesfullyCreated = true;
                $scope.messageData = data;

                // Reset form model
                $scope.centuria = new CenturiaFactory();
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