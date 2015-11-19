/**
 * Created by Martin Eckardt on 16.11.2015.
 * Controller to edit a course
 */
angular.module('ttmg.controllers').controller('editCourseController', ['$scope', '$q', '$routeParams', 'CourseFactory', 'CenturiaFactory', 'TutorFactory', function ($scope, $q, $routeParams, CourseFactory, CenturiaFactory, TutorFactory) {

    // Route parameters
    var courseId = $routeParams.id;
    console.log('editCourseController for Course ' + courseId + ' started');


    // Set up model
    $scope.model = {
        tutors: TutorFactory.query()
    };

    // Wait for the both resources to load
    $q.all([
        CourseFactory.get({courseId: courseId},
            function successCallback(course) {
                $scope.model.course = course;
            }
        ).$promise,
        CenturiaFactory.query(
            function successCallback(centurias) {
                $scope.model.centurias = centurias;
            }
        ).$promise
    ]).then(function () {
        var participants = $scope.model.course.participants;
        var centurias = $scope.model.centurias;

        // select centurias that are in participants
        participants.forEach(function (participant) {
            // Use for to break when participant matches the centuria
            for (var i = 0; i < centurias.length; i++) {
                if (centurias[i].id == participant.id) {
                    centurias[i].selected = true;
                    break;
                }
            }
        });
    });


    var course = $scope.model.course;


    this.updateCourse = function () {
        // Reset values
        course.participants = {};

        if (course.type != 'SEMINAR') { // if the course is not a seminar
            // Add selected centruias to course
            course.participants = $scope.model.centurias.filter(function filter(participant) {
                return participant.selected == true;
            });
        }

        course.$update({courseId: course.id}, function successCallback(data) {
            console.log("Course successfully updated");
            console.log(data);

            // Reset form
            $scope.model.course = new CourseFactory();

            // Fill messageData with newly created entity
            $scope.entitySuccesfullyCreated = true;
            $scope.messageData = data;
        }, function errorCallback(error) {
            console.log("Failed to update course");
            console.log(error);

            // Fill messageData with exception message from backend
            $scope.entitySuccesfullyCreated = false;
            $scope.messageData = error.data.message;
        });


    };
}]);


