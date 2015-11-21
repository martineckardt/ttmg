/**
 * Created by Martin Eckardt on 16.11.2015.
 * Controller to create a course
 */
angular.module('ttmg.controllers').controller('createCourseController',
    ['$scope', 'CourseFactory', 'CenturiaFactory', 'TutorFactory', 'COURSE_TYPES',
        function ($scope, CourseFactory, CenturiaFactory, TutorFactory, COURSE_TYPES) {

    console.log('createCourseController initialized');

            console.log('course types: ' + COURSE_TYPES)

    // Set up model
    $scope.model = {
        course: new CourseFactory(),
        tutors: TutorFactory.query(),
        centurias: CenturiaFactory.query(),
        courseTypes: COURSE_TYPES
    };

    var course = $scope.model.course;


    this.addCourse = function () {
        // Reset values
        course.participants = {};

        if (course.type != 'SEMINAR') { // if the course is not a seminar
            // Add selected centruias to course
            course.participants = $scope.model.centurias.filter(function filter(participant) {
                return participant.selected == true;
            });
        }

        course.$create(function successCallback(data) {
            console.log("Course successfully created");
            console.log(data);

            // Reset form
            $scope.model.course = new CourseFactory();

            // Fill messageData with newly created entity
            $scope.entitySuccesfullyCreated = true;
            $scope.messageData = data;
        }, function errorCallback(error) {
            console.log("Failed to create course");
            console.log(error);

            // Fill messageData with exception message from backend
            $scope.entitySuccesfullyCreated = false;
            $scope.messageData = error.data.message;
        });


    };
}]);


