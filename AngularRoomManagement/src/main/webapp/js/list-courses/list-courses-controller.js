/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('listCoursesController', ['$scope', 'courseService', function ($scope, courseService) {

    console.log('listCoursesController started');

    // Setup scope model
    $scope.model = {
        courses: []
    };

    // Load rooms from REST API
    courseService.listCoursesWithPromise().then(function successCallback(response) {
        $scope.model.courses = response.data;
    }, function errorCallback(response) {
        console.log('failed to query courses');
    });
}]);