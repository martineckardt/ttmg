/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('listCoursesController', ['$scope', 'CourseFactory', function ($scope, CourseFactory) {

    console.log('listCoursesController started');

    // Setup scope model
    $scope.model = {
        courses: CourseFactory.query(function successCallback(data) {
            // Logging
            console.log("Successfully queried entities");
            console.log(data);
        }, function errorCallback(error) {
            // Logging
            console.log("Error loading entities:");
            console.log(error);

            // TODO Error handling
        })
    };
}]);