/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('viewTutorController', ['$scope', '$routeParams', 'TutorFactory', 'EventFactory', function ($scope, $routeParams, TutorFactory, EventFactory) {

    // Route parameters
    var tutorId = $routeParams.id;
    console.log('viewTutorController for Tutor ' + tutorId + ' started');

    $scope.model = {
        tutor: TutorFactory.get({tutorId: tutorId}),
        events: EventFactory.query({tutorId: tutorId})
    };
}]);