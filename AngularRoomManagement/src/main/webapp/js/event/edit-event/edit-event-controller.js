/**
 * Created by Martin Eckardt on 19.11.2015.
 * Controller to edit a single event.
 */

angular.module('ttmg.controllers').controller('editEventController',
    ['$scope', '$routeParams', 'EventResourceFactory', 'RoomFactory',
        function ($scope, $routeParams, EventResourceFactory, RoomFactory) {

            // Route parameters
            var eventId = $routeParams.id;
            console.log('editEventController for Event ' + eventId + ' started');

            $scope.formState = 1;

            // Set up model
            $scope.model = {
                event: EventResourceFactory.get({eventId: eventId},
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

                var event = $scope.model.event;

                $scope.formModel.availableRooms = RoomFactory.query({
                    freeStart: baseDateBeginString,
                    freeEnd: endDateString,
                    minSeats: event.course.numberOfStudents,
                    ignoreEventId: event.id
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


            this.tryUpdateEvent = function () {
                var event = $scope.model.event;

                // Reset rooms
                event.rooms = {};

                // Add selected rooms
                event.rooms = $scope.formModel.availableRooms.filter(function filter(room) {
                    return room.selected == true;
                });

                updateEvent(false);
            };

            this.forceUpdateEvent = function () {
                updateEvent(true);
            };

            this.goBackToStep = function (stepId) {
                $scope.formState = stepId;
            };

            function updateEvent(force) {
                console.log('attempting to update event with force = ' + force);
                var event = $scope.model.event;

                event.$update(
                    {eventId: event.id, courseId: event.course.id, force: force},
                    function successCallback(data) {
                        console.log("Event successfully updated");
                        console.log(data);

                        // Fill messageData with newly created entity
                        $scope.entitySuccesfullyUpdate = true;
                        $scope.messageData = data;

                        $scope.formState = 5;
                    },
                    function errorCallback(error) {
                        console.log("Failed to update course");
                        console.log(error);

                        $scope.entitySuccesfullyUpdate = false;

                        // Check if backend indicates conflicts or
                        if (error.data.localizableMessage == 'TIME_CONFLICTS') {
                            console.log('conflicts detected');
                            $scope.conflicts = error.data.conflicts;

                            // Go to conflicts page
                            console.log("go to conflicts view");
                            $scope.formState = 3;
                        } else if (error.data.ignorable) {
                            console.log('ignorable error detected');
                            $scope.ignorableError = error.data;
                            $scope.formState = 4;
                        } else {
                            $scope.messageData = error.data;
                            // Go to unexpected error page
                            $scope.formState = 5;
                        }

                    }
                );
            }

        }]);


