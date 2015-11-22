/**
 * Created by Martin Eckardt on 19.11.2015.
 * Controller to edit a single event.
 */

angular.module('ttmg.controllers').controller('editEventController',
    ['$scope', '$routeParams', 'EventFactory', 'RoomFactory',
        function ($scope, $routeParams, EventFactory, RoomFactory) {

            // Route parameters
            var eventId = $routeParams.id;
            console.log('editEventController for Event ' + eventId + ' started');

            $scope.formState = 1;

            // Set up model
            $scope.model = {
                event: EventFactory.get({eventId: eventId},
                    function successCallback(event) {
                        var beginDate = new Date(event.begin);
                        var endDate = new Date(event.end);

                        $scope.formModel = {
                            baseDate: {
                                date: beginDate,
                                begin: {
                                    hours: beginDate.getHours(),
                                    minutes: beginDate.getMinutes()
                                },
                                end: {
                                    hours: endDate.getHours(),
                                    minutes: endDate.getMinutes()
                                }
                            }
                        };
                    },
                    function errorCallback(error) {
                        // TODO
                    }
                )
            };


            this.proceedToRoomSelection = function () {
                var eventDate = $scope.formModel.baseDate;
                // Construct base dates and their string
                var beginDate = new Date(eventDate.date);
                beginDate.setHours(eventDate.begin.hours);
                beginDate.setMinutes(eventDate.begin.minutes);
                var baseDateBeginString = $.format.date(beginDate, 'yyyy-MM-dd_HH:mm');

                var endDate = new Date(eventDate.date);
                endDate.setHours(eventDate.end.hours);
                endDate.setMinutes(eventDate.end.minutes);
                var endDateString = $.format.date(endDate, 'yyyy-MM-dd_HH:mm');

                $scope.formModel.availableRooms = RoomFactory.query({
                    freeStart: baseDateBeginString,
                    freeEnd: endDateString,
                    minSeats: $scope.model.event.course.numberOfStudents
                }, function successCallback(availableRooms) {
                    var eventRooms = $scope.model.event.rooms;

                    // select rooms that are booked for the event
                    eventRooms.forEach(function (participant) {
                        // Use for to break when room from event matches the available room
                        for (var i = 0; i < availableRooms.length; i++) {
                            if (availableRooms[i].id == participant.id) {
                                availableRooms[i].selected = true;
                                break;
                            }
                        }
                    });
                });

                $scope.formState = 2;
            };


            this.updateEvent = function () {
                var event = $scope.model.event;
                // Reset values
                event.rooms = {};

                event.rooms = $scope.formModel.availableRooms.filter(function filter(room) {
                    return room.selected == true;
                });

                event.$update({eventId: event.id, courseId: event.course.id}, function successCallback(data) {
                    console.log("Event successfully updated");
                    console.log(data);

                    // Fill messageData with newly created entity
                    $scope.entitySuccesfullyCreated = true;
                    $scope.messageData = data;
                }, function errorCallback(error) {
                    console.log("Failed to update event");
                    console.log(error);

                    // Fill messageData with exception message from backend
                    $scope.entitySuccesfullyCreated = false;
                    $scope.messageData = error.data.message;
                });
            };
        }]);


