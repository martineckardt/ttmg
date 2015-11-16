angular.module('ttmg.controllers').controller('createCourseController', ['$scope', 'CourseFactory', 'CenturiaFactory', 'TutorFactory', 'RoomFactory', function ($scope, CourseFactory, CenturiaFactory, TutorFactory, RoomFactory) {
    console.log('createCourseController initialized');

    $scope.formState = 1;

    // Set up model
    $scope.model = {
        course: new CourseFactory(),
        tutors: TutorFactory.query(),
        centurias: CenturiaFactory.query(),
        eventForDateSelection: {
            begin: new Date(),
            end: new Date(),
            repeatForWeeks: 1
        },
        eventsForRoomSelection: []
    };

    var course = $scope.model.course;
    var eventForDateSelection = $scope.model.eventForDateSelection;
    var eventsForRoomSelection = $scope.model.eventsForRoomSelection;

    this.proceedToEventSelection = function () {
        // Reset values
        course.participants = {};
        var numberOfParticipants = 0;

        if (course.type != 'SEMINAR') { // if the course is not a seminar
            // Add selected centruias to course
            course.participants = $scope.model.centurias.filter(function filter(participant) {
                return participant.selected == true;
            });

            //  Calculate the total number of participants for the querying of the room
            course.participants.forEach(function (currentParticipant) {
                numberOfParticipants += currentParticipant.nbrOfStudents;
            });
        }

        $scope.model.numberOfParticipants = numberOfParticipants;
        $scope.formState = 2;
    };

    this.proceedToRoomSelection = function () {
        // Reset room selection
        eventsForRoomSelection.splice(0, eventsForRoomSelection.length);

        // create events
        for (repetitionIndex = 0; repetitionIndex < eventForDateSelection.repeatForWeeks; repetitionIndex++) {
            var currentBegin = new Date();
            var currentEnd = new Date();
            // Query available rooms for this event using the number of participants
            // TODO Free start, free end
            var currentAvailableRooms = RoomFactory.query({
                //freeStart: currentBegin.getTime(),
                //freeEnd: currentEnd.getTime(),
                minSeats: $scope.model.numberOfParticipants
            });

            var currentEvent = {
                begin: currentBegin,
                end: currentEnd,
                availableRooms: currentAvailableRooms
            };
            eventsForRoomSelection.push(currentEvent);
        }
        $scope.formState = 3;
    };

    this.addCourse = function () {
        // create course events from selection
        var courseEvents = [];
        eventsForRoomSelection.forEach(function (currentEvent) {
            // Filter the selected rooms
            var selectedRoomsForCurrentEvent = currentEvent.availableRooms.filter(function selectedFilter(room) {
                return room.selected == true;
            });

            var event = {
                begin: currentEvent.begin,
                end: currentEvent.end,
                rooms: selectedRoomsForCurrentEvent
            };
            courseEvents.push(event);
        });

        course.events = courseEvents;

        course.$create(function successCallback(data) {
            console.log("Course successfully created");
            console.log(data);
        }, function errorCallback(error) {
            console.log("Failed to create course");
            console.log(error);
        });

        $scope.formState = 4;
    };

    this.goBackToStep = function (stepId) {
        $scope.formState = stepId;
    };

}]);

/**
 * Created by Martin Eckardt on 16.11.2015.
 */
