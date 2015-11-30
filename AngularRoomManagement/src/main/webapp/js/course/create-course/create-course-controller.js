/**
 * Created by Martin Eckardt on 16.11.2015.
 * Controller to create a course.
 */
angular.module('ttmg.controllers').controller('createCourseController',
    ['$scope', 'CourseResourceFactory', 'CenturiaResourceFactory', 'TutorResourceFactory', 'COURSE_TYPES',
        function ($scope, CourseResourceFactory, CenturiaResourceFactory, TutorResourceFactory, COURSE_TYPES) {

            console.log('createCourseController initialized');

            // Set up model
            $scope.model = {
                course: new CourseResourceFactory(),
                tutors: TutorResourceFactory.query(),
                centurias: CenturiaResourceFactory.query(),
                courseTypes: COURSE_TYPES
            };

            // Commit the entered data to backend
            this.addCourse = function () {
                // Convenience access
                var course = $scope.model.course;

                // Reset values
                course.participants = [];

                if (course.type != 'SEMINAR') { // if the course is not a seminar
                    // Add selected centurias to course
                    course.participants = $scope.model.centurias.filter(function filter(participant) {
                        return participant.selected == true;
                    });
                }

                course.$create({}, function successCallback(data) {
                    console.log("Course successfully created");
                    console.log(data);

                    // Fill messageData with newly created entity
                    $scope.entitySuccesfullyCreated = true;
                    $scope.messageData = data;

                    // Reset form
                    $scope.model.course = new CourseResourceFactory();
                    // Set untouched in ng-click, since here it throws a weird error
                    //$scope.courseForm.$setUntouched();
                }, function errorCallback(error) {
                    console.log("Failed to create course");
                    console.log(error);

                    // Fill messageData with exception message from backend
                    $scope.entitySuccesfullyCreated = false;
                    $scope.messageData = error.data;
                });
            };
        }]);


