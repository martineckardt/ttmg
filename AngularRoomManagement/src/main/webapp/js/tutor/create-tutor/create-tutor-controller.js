/**
 * Created by Martin Eckardt on 28.10.2015.
 * Controller for creation of entities
 */

angular.module('ttmg.controllers').controller('createTutorController', ['$scope', 'TutorFactory', function ($scope, TutorFactory) {
    console.log('createTutorController initialized');

    // Set up form model
    $scope.model = {
        tutor: new TutorFactory()
    };

    //create a new tutor
    $scope.addTutor = function () {
        $scope.model.tutor.$create(
            function successCallback(data) {
                // Logging
                console.log("entity sucessfully created");
                console.log(data);

                // Fill messageData with newly created entity
                $scope.entitySuccesfullyCreated = true;
                $scope.messageData = data;

                // Reset form model
                $scope.model.tutor = new TutorFactory();
                $scope.tutorForm.$setUntouched();
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