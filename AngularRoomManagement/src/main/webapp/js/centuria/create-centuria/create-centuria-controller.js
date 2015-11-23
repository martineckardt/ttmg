/**
 * Created by Martin Eckardt on 28.10.2015.
 * Controller for creation of a centuria.
 */

angular.module('ttmg.controllers').controller('createCenturiaController', ['$scope', 'CenturiaFactory', 'STUDY_PROGRAMS', function ($scope, CenturiaFactory, STUDY_PROGRAMS) {
    console.log('createCenturiaController initialized');

    // Set up form model
    $scope.model = {
        centuria: new CenturiaFactory(),
        studyPrograms: STUDY_PROGRAMS
    };

    //create a new centuria
    $scope.addCenturia = function () {
        $scope.model.centuria.$create(
            function successCallback(data) {
                // Logging
                console.log("entity sucessfully created");
                console.log(data);

                // Fill messageData with newly created entity
                $scope.entitySuccesfullyCreated = true;
                $scope.messageData = data;

                // Reset form model
                $scope.model.centuria = new CenturiaFactory();
                $scope.centuriaForm.$setUntouched();
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