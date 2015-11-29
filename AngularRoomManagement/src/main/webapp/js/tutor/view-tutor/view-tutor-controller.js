/**
 * Created by Martin Eckardt on 28.10.2015.
 * Controller to view a single tutor
 */

angular.module('ttmg.controllers').controller('viewTutorController', ['$scope', '$routeParams', 'TutorResourceFactory', 'EventResourceFactory', function ($scope, $routeParams, TutorResourceFactory, EventResourceFactory) {

    // Route parameters
    var tutorId = $routeParams.id;
    console.log('viewTutorController for Tutor ' + tutorId + ' started');

    $scope.model = {
        tutor: TutorResourceFactory.get({tutorId: tutorId},
            function successCallback() {
                $scope.entityFound = true;
            },
            function errorCallback(error) {
                $scope.entityFound = false;
                $scope.error = error.data;
            }
        ),
        events: EventResourceFactory.query({tutorId: tutorId})
    };
}]);