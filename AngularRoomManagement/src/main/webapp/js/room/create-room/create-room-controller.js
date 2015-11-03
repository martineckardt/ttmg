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
        formRoom: new Room(null, null, null, null, null)
    };

    /**
     * Saves the changes.
     * @param roomForm The form object of the room.
     */
    this.saveRoom = function (roomForm) {
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

}]);