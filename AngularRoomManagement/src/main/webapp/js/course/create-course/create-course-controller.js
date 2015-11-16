/**
 * Created by Martin Eckardt on 16.11.2015.
 */

angular.module('ttmg.controllers').controller('createCourseController', ['$scope', 'CourseFactory', 'CenturiaFactory', 'TutorFactory', function ($scope, CourseFactory, CenturiaFactory, TutorFactory) {
    console.log('createCourseController initialized');

    $scope.formState = 1;

    // Set up model
    $scope.model = {
        course: new CourseFactory(),
        tutors: TutorFactory.query(),
        centurias: CenturiaFactory.query()
    };

    this.proceedToEventSelection = function () {
        var course = $scope.model.course;
        var numberOfParticipants = 0;

        if (course.type != 'SEMINAR') {
            // Add selected centruias to course
            course.participants = $scope.model.centurias.filter(function filter(participant) {
                return participant.selected == true;
            });

            //  Calculate the total number of participants for the querying of the room
            course.participants.forEach(function (currentParticipant) {
                numberOfParticipants += currentParticipant.nbrOfStudents;
            });
        }
        else { // The course is a seminar
            // Delete all previously selected centurias
            course.participants = {};
        }

        $scope.model.numberOfParticipants = numberOfParticipants;
        $scope.formState = 2;

        $scope.model.course.events = {
            1: {
                begin: new Date(),
                end: new Date()
            }
        }

    };

}]);