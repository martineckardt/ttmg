/**
 * Created by Martin Eckardt on 16.11.2015.
 * Controller to edit a course
 */
angular.module('ttmg.controllers').controller('editCourseController',
    ['$scope', '$q', '$location', '$routeParams', 'CourseResourceFactory', 'CenturiaResourceFactory', 'TutorResourceFactory', 'COURSE_TYPES',
        function ($scope, $q, $location, $routeParams, CourseResourceFactory, CenturiaResourceFactory, TutorResourceFactory, COURSE_TYPES) {

            // Route parameters
            var courseId = $routeParams.id;
            console.log('editCourseController for Course ' + courseId + ' started');

            // Set form state
            $scope.formState = 1;


            // Set up model
            $scope.model = {
                course: CourseResourceFactory.get({courseId: courseId},
                    function successCallback() {
                        $scope.entityFound = true;
                    },
                    function errorCallback(error) {
                        $scope.entityFound = false;
                        $scope.error = error.data;
                    }),
                tutors: TutorResourceFactory.query(),
                courseTypes: COURSE_TYPES,
                centurias: CenturiaResourceFactory.query()
            };

            // Wait for the both resources to load
            $q.all([
                $scope.model.course.$promise,
                $scope.model.centurias.$promise
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


            this.tryUpdateCourse = function () {
                var course = $scope.model.course;

                // Reset participants
                course.participants = {};

                if (course.type != 'SEMINAR') { // if the course is not a seminar

                    // Add selected centruias to course
                    course.participants = $scope.model.centurias.filter(function filter(participant) {
                        return participant.selected == true;
                    });
                }

                updateCourse(false);
            };

            this.forceUpdateCourse = function () {
                updateCourse(true);
            };

            this.goBackToStep = function (stepId) {
                $scope.formState = stepId;
            };


            function updateCourse(force) {
                var course = $scope.model.course;

                // Update the course
                course.$update(
                    {courseId: course.id, force: force},
                    function successCallback(data) {
                        console.log("Course successfully updated");
                        console.log(data);

                        // Fill messageData with newly created entity
                        $scope.entitySuccesfullyUpdated = true;
                        $scope.messageData = data;

                        // Set form state
                        $scope.formState = 4;
                    },
                    function errorCallback(error) {
                        console.log("Failed to update course");
                        console.log(error);
                        $scope.entitySuccesfullyUpdated = false;

                        // Check if backend indicates conflicts or
                        if (error.data.localizableMessage == 'TIME_CONFLICTS') {
                            console.log('conflicts detected');
                            $scope.conflicts = error.data.conflicts;

                            // Go to conflicts page
                            console.log("go to conflicts view");
                            $scope.formState = 2;
                        } else if (error.data.ignorable) {
                            console.log('insufficient seats detected');
                            // Fill messageData with exception message from backend
                            $scope.entitySuccesfullyUpdated = false;
                            $scope.ignorableError = error.data;
                            console.log($scope.ignorableError);

                            // Go to ignorable error page
                            $scope.formState = 3;
                        } else {
                            $scope.messageData = error.data;
                            // Go to unexpected error page
                            $scope.formState = 4;
                        }
                    }
                );
            }
        }
    ]
);


