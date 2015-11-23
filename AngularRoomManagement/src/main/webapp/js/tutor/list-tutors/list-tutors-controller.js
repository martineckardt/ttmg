/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('listTutorsController', ['$scope', 'TutorResourceFactory', function ($scope, TutorResourceFactory) {

    console.log('listTutorsController started');

    // Setup scope model
    $scope.model = [];

    // Load rooms from REST API
    TutorResourceFactory.query(function successCallback(data) {
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