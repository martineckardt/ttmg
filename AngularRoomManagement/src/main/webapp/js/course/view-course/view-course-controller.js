/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('viewCourseController', ['$scope', '$routeParams', 'CourseFactory', 'EventFactory', function ($scope, $routeParams, CurseFactory, EventFactory) {

    // Route parameters
    var courseId = $routeParams.id;
    console.log('viewCourseController for Course ' + courseId + ' started');

    // Set up scope model
    $scope.model = {
        course: CurseFactory.get({id: courseId}),
        events: EventFactory.query({courseId: courseId})
    };

    this.deleteCourse = function () {
        $scope.model.course.$delete({id: courseId},
            function successCallback(data) {
                console.log("successfully deleted course");
                console.log(data);
            }, function errorCallback(error) {
                console.log("failed to delete course");
                console.log(error);
            });
    };
}]);