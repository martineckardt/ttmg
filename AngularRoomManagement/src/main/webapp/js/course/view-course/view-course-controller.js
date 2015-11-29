/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('viewCourseController', ['$scope', '$routeParams', 'CourseResourceFactory', 'EventResourceFactory', function ($scope, $routeParams, CourseResourceFactory, EventResourceFactory) {

    // Route parameters
    var courseId = $routeParams.id;
    console.log('viewCourseController for Course ' + courseId + ' started');

    // Set up scope model
    $scope.model = {
        course: CourseResourceFactory.get({courseId: courseId},
            function successCallback() {
                $scope.entityFound = true;
            },
            function errorCallback(error) {
                $scope.entityFound = false;
                $scope.error = error.data;
            }
        ),
        events: EventResourceFactory.query({courseId: courseId})
    };


}]);