/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('listTutorsController', ['$scope', 'TutorFactory', function ($scope, TutorFactory) {

    console.log('listTutorsController started');

    // Setup scope model
    $scope.model = [];

    // Load rooms from REST API
    TutorFactory.query(function successCallback(data) {
        // Logging
        console.log("Successfully queried entities");
        console.log(data);

        // Set model
        $scope.model.tutors = data;
    }, function errorCallback(error) {
        // Logging
        console.log("Error loading entities:");
        console.log(error);

        // TODO Error handling
    });
}]);