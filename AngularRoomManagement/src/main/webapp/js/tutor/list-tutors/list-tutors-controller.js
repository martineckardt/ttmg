/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('listTutorsController', ['$scope', 'TutorFactory', function ($scope, TutorFactory) {

    console.log('listTutorsController started');

    // Setup scope model
    $scope.model = [];

    // Load rooms from REST API
    TutorFactory.query(function (data) {
        $scope.model.tutors = data;
    }, function (error) {
        console.log("error:");
        console.log(error);
    });
}]);