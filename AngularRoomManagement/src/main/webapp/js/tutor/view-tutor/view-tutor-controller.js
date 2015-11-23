/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('viewTutorController', ['$scope', '$routeParams', 'TutorResourceFactory', 'EventResourceFactory', function ($scope, $routeParams, TutorResourceFactory, EventResourceFactory) {

    // Route parameters
    var tutorId = $routeParams.id;
    console.log('viewTutorController for Tutor ' + tutorId + ' started');

    $scope.model = {
        tutor: TutorResourceFactory.get({tutorId: tutorId}),
        events: EventResourceFactory.query({tutorId: tutorId})
    };
}]);