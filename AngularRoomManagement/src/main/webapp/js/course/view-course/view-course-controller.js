/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('showCourseController', ['$scope', '$routeParams', 'courseService', function ($scope, $routeParams, courseService) {

    console.log('showCourseController for Room ' + $routeParams.id + 'started');

    $scope.model = [];

    // Load rooms from REST API
    courseService.getCourseWithPromise($routeParams.id).then(function successCallback(response) {
        $scope.model.course = response.data;
    }, function errorCallback(response) {
        console.log('failed to query courses');
    });
}]);