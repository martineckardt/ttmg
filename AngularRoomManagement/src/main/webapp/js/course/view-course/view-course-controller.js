/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('viewCourseController', ['$scope', '$routeParams', 'CourseFactory', 'EventFactory', function ($scope, $routeParams, CourseFactory, EventFactory) {

    // Route parameters
    var courseId = $routeParams.id;
    console.log('viewCourseController for Course ' + courseId + ' started');

    // Set up scope model
    $scope.model = {
        course: CourseFactory.get({courseId: courseId}),
        events: EventFactory.query({courseId: courseId})
    };


}]);