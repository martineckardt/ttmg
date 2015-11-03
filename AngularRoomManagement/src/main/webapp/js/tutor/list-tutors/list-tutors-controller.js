/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('listTutorsController', ['$scope', 'tutorService', function ($scope, tutorService) {

    console.log('listTutorsController started');

    // Setup scope model
    $scope.model = {
        tutors: []
    };

    // Load rooms from REST API
    tutorService.listTutorsWithPromise().then(function successCallback(response) {
        $scope.model.tutors = response.data;
        console.log($scope.model.rooms);
    }, function errorCallback(response) {
        console.log('failed to query tutors');
    });
}]);