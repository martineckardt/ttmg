/**
 * Created by Martin Eckardt on 19.11.2015.
 * Controller to create multiple events for a course
 */

angular.module('ttmg.controllers').controller('createCourseEventsController',
    ['$scope', '$routeParams', 'EventFactory', 'CourseFactory', 'RoomFactory',
        function ($scope, $routeParams, EventFactory, CourseFactory, RoomFactory) {

            // Route parameters
            var courseId = $routeParams.id;
            console.log('createCourseEventsController for Course ' + courseId + ' started');

            $scope.formState = 1;

            // Set up model
            $scope.model = {
                course: CourseFactory.get({courseId: courseId}),
                events: []
            };

            $scope.formModel = {
                eventForDateSelection: {
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


            var eventForDateSelection = $scope.formModel.eventForDateSelection;
            var eventsForRoomSelection = $scope.formModel.eventsForRoomSelection;

            this.proceedToRoomSelection = function () {
                // Reset room selection
                eventsForRoomSelection.splice(0, eventsForRoomSelection.length);

                var baseDateBegin = new Date(eventForDateSelection.date);
                baseDateBegin.setHours(eventForDateSelection.begin.hours);
                baseDateBegin.setMinutes(eventForDateSelection.begin.minutes);

                var baseDateEnd = new Date(eventForDateSelection.date);
                baseDateEnd.setHours(eventForDateSelection.end.hours);
                baseDateEnd.setMinutes(eventForDateSelection.end.minutes);

                // create events
                for (var repetitionIndex = 0; repetitionIndex < eventForDateSelection.repeatForWeeks; repetitionIndex++) {
                    var currentBegin = new Date(baseDateBegin);
                    currentBegin.setDate(baseDateBegin.getDate() + (7 * repetitionIndex));

                    var currentEnd = new Date(baseDateEnd);
                    currentEnd.setDate(baseDateEnd.getDate() + (7 * repetitionIndex));

                    // Query available rooms for this event using the number of participants
                    // TODO Free start, free end
                    var currentAvailableRooms = RoomFactory.query({
                        //freeStart: currentBegin.getTime(),
                        //freeEnd: currentEnd.getTime(),
                        minSeats: course.numberOfStudents
                    });

                    var currentEvent = {
                        begin: currentBegin,
                        end: currentEnd,
                        availableRooms: currentAvailableRooms
                    };
                    eventsForRoomSelection.push(currentEvent);
                }
                $scope.formState = 2;
            };

            this.addEvents = function () {
                var events = $scope.model.events;

                // Add the selected rooms to the events
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
                    events.push(event);
                });

                var eventsRessource = new EventFactory(events);
                eventsRessource.$bulkCreate({courseId: course.id}, function successCallback(data) {
                    console.log("Events successfully created");
                    console.log(data);

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

                $scope.formState = 3;
            };

            this.goBackToStep = function (stepId) {
                $scope.formState = stepId;
            };

        }]);


