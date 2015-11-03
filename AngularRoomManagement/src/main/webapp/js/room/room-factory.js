/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg').factory('Room', function () {
    return function (id, building, roomNumber, seats, beamerPresent) {
        this.id = id;
        this.building = building;
        this.roomNumber = roomNumber;
        this.seats = seats;
        this.beamerPresent = beamerPresent;
    };
});
