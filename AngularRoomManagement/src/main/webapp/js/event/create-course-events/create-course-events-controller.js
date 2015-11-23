/**
 * Created by Martin Eckardt on 19.11.2015.
 * Controller to create multiple events for a course. The form has 3 steps
 */

angular.module('ttmg.controllers').controller('createCourseEventsController',
    ['$scope', '$routeParams', 'EventResourceFactory', 'CourseResourceFactory', 'RoomFactory',
        function ($scope, $routeParams, EventResourceFactory, CourseResourceFactory, RoomFactory) {

            // Route parameters
            var courseId = $routeParams.id;
            console.log('createCourseEventsController for Course ' + courseId + ' started');

            $scope.formState = 1;
            var eventsResource;

            // Set up model
            $scope.model = {
                course: CourseResourceFactory.get({courseId: courseId}),
                events: []
            };

            $scope.formModel = {
                baseDate: {
                    date: new Date(),
                    begin: {
                        hours: 09,
                        minutes: 15
                    },
                    end: {
                        hours: 12,
                        minutes: 30
                    },
                    repeatForWeeks: 9
                },
                eventsForRoomSelection: []
            };

            var course = $scope.model.course;

            var baseDate = $scope.formModel.baseDate;

            this.proceedToRoomSelection = function () {
                // Reset room selection
                var eventsForRoomSelection = [];

                // Construct base dates
                var baseDateBegin = new Date(baseDate.date);
                baseDateBegin.setHours(baseDate.begin.hours);
                baseDateBegin.setMinutes(baseDate.begin.minutes);

                var baseDateEnd = new Date(baseDate.date);
                baseDateEnd.setHours(baseDate.end.hours);
                baseDateEnd.setMinutes(baseDate.end.minutes);

                // create events for room selection
                for (var repetitionIndex = 0; repetitionIndex < baseDate.repeatForWeeks; repetitionIndex++) {

                    // Construct dates relativ to base date
                    var currentBegin = new Date(baseDateBegin);
                    currentBegin.setDate(baseDateBegin.getDate() + (7 * repetitionIndex));

                    var currentEnd = new Date(baseDateEnd);
                    currentEnd.setDate(baseDateEnd.getDate() + (7 * repetitionIndex));

                    // Query available rooms for this event using the number of participants

                    var currentBeginString = $.format.date(currentBegin, 'yyyy-MM-dd_HH:mm');
                    var currentEndString = $.format.date(currentEnd, 'yyyy-MM-dd_HH:mm');

                    var currentAvailableRooms = RoomFactory.query({
                        freeStart: currentBeginString,
                        freeEnd: currentEndString,
                        minSeats: course.numberOfStudents
                    });

                    var currentEvent = {
                        begin: currentBegin,
                        end: currentEnd,
                        availableRooms: currentAvailableRooms
                    };
                    eventsForRoomSelection.push(currentEvent);
                }

                $scope.formModel.eventsForRoomSelection = eventsForRoomSelection;
                $scope.formState = 2;
            };

            this.tryAddEvents = function () {
                eventsResource = new EventResourceFactory(createEventsFromFormModel());
                eventsResource.$bulkCreate({courseId: course.id}, function successCallback(data) {
                    console.log("Events successfully created");
                    console.log(data);

                    // Fill messageData with newly created entity
                    $scope.entitySuccessfullyCreated = true;
                    $scope.messageData = data;

                    // Go to success page
                    $scope.formState = 4;
                }, function errorCallback(error) {
                    console.log("Failed to create course events");
                    console.log(error);

                    // Check if backend indicates conflicts
                    if (error.data.ignorable) {
                        console.log('conflicts detected');

                        $scope.conflicts = error.data.conflicts;

                        // Go to conflicts page
                        console.log("go to conflicts view");
                        $scope.formState = 3;
                    } else {
                        console.log('error detected');
                        // Fill messageData with exception message from backend
                        $scope.entitySuccessfullyCreated = false;
                        $scope.messageData = error.data;

                        // Go to error page
                        $scope.formState = 4;
                    }
                });
            };

            this.forceAddEvents = function () {
                console.log('attempting to create events with force');
                eventsResource.$bulkCreate({courseId: course.id, force: true}, function successCallback(data) {
                    console.log("Events successfully created with force");
                    console.log(data);

                    // Fill messageData with newly created entity
                    $scope.entitySuccessfullyCreated = true;
                    $scope.messageData = data;
                }, function errorCallback(error) {
                    console.log("Failed to create course events with force");
                    console.log(error);

                    // Fill messageData with exception message from backend
                    $scope.entitySuccessfullyCreated = false;
                    $scope.messageData = error.data;
                });
                // Go to error / success page
                $scope.formState = 4;
            };

            this.goBackToStep = function (stepId) {
                $scope.formState = stepId;
            };

            this.removeEventForRoomSelection = function (index) {
                eventsForRoomSelection.splice(index, 1);
            };

            // Private functions

            function createEventsFromFormModel() {
                var events = [];

                // Add the selected rooms to the events
                $scope.formModel.eventsForRoomSelection.forEach(function (currentEvent) {
                    // Filter the selected rooms
                    var selectedRoomsForCurrentEvent = currentEvent.availableRooms.filter(function selectedFilter(room) {
                        return room.selected == true;
                    });

                    var event = {
                        begin: currentEvent.begin,
                        end: currentEvent.end,
                        rooms: selectedRoomsForCurrentEvent
                    };
                    events.push(event);
                });

                return events;
            };

        }]);


