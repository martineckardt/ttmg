/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('createRoomController', ['$scope', 'Room', 'roomService', function ($scope, Room, roomService) {

    console.log('createRoomController initialized');

    // Object containing the error messages.
    var messages = {
        errors: {
            required: 'Please enter a value!',
            number: 'Please enter a number!',
            min: 'The number is smaller than the minimum allowed!',
            unknown: 'Please enter a valid value!'
        }
    };

    // Set up the form model.
    $scope.formModel = {
        isEdit: $scope.model.selectedRoom.building && $scope.model.selectedRoom.roomNumber,
        formRoom: new Room($scope.model.selectedRoom.id, $scope.model.selectedRoom.building,
            $scope.model.selectedRoom.roomNumber, $scope.model.selectedRoom.seats,
            $scope.model.selectedRoom.beamerPresent)
    };

    /**
     * Cancels the editing.
     */
    this.cancel = function () {
        $scope.switchToScreen($scope.screens.mainScreen);
    };

    /**
     * Saves the changes.
     * @param roomForm The form object of the room.
     */
    this.saveRoom = function (roomForm) {
        var selected = $scope.model.selectedRoom;
        var edited = $scope.formModel.formRoom;
        if (roomForm.$valid && selected && edited) {
            selected.id = edited.id;
            selected.building = edited.building;
            selected.roomNumber = edited.roomNumber;
            selected.seats = edited.seats;
            selected.beamerPresent = edited.beamerPresent;
            // do save data
            roomService.saveRoomWithPromise(selected)
                .success(function (data, status, headers, config) {
                    if ($scope.model.rooms.indexOf(selected) === -1) {
                        $scope.model.rooms.push(data);
                    }
                    $scope.switchToScreen($scope.screens.mainScreen);
                }).error(function (data, status, headers, config) {
                    alert("an error occured while saving");
                });
        }
    };

    /**
     * Returns the error message for the given element.
     * @param element The element.
     * @returns a string.
     */
    this.getErrorMessage = function (element) {
        var message = null;
        if (element.$error) {
            if (element.$error.required) {
                message = messages.errors.required;
            }
            else if (element.$error.number) {
                message = messages.errors.number;
            }
            else if (element.$error.min) {
                message = messages.errors.min;
            }
            else {
                message = messages.errors.unknown;
            }
        }
        return message;
    };
}]);