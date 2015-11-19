/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('viewTutorController', ['$scope', '$routeParams', 'TutorFactory', function ($scope, $routeParams, TutorFactory) {

    // Route parameters
    var tutorId = $routeParams.id;
    console.log('viewTutorController for Tutor ' + tutorId + ' started');

    $scope.model = {
        tutor: TutorFactory.get({tutorId: tutorId}),
        events: TutorFactory.getEvents({tutorId: tutorId})
    };
}]);