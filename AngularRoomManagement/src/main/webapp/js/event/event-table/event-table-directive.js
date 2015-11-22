/**
 * Created by Martin Eckardt on 03.11.2015.
 * Directive for displaying a set of rooms in a table.
 */

angular.module('ttmg').directive('eventTable', function () {
    return {
        restrict: 'E',
        scope: {events: '='},
        controller: ['$scope', '$window', function ($scope, $window) {
            console.log("eventTableController started");

            this.deleteEvent = function (event) {
                console.log("attempting to delete event " + event.id);
                event.$delete({eventId: event.id},
                    function successCallback(data) {
                        console.log("successfully deleted entity");
                        console.log(data);

                        // Hide the modal
                        $('#deleteEntityModal-event-' + event.id).modal('hide');

                        // Redirecting to successHref
                        $window.location.reload();
                    }, function errorCallback(error) {
                        console.log("failed to delete entity");
                        console.log(error);
                        // Show error message
                        $scope.deleteEntityErrorMessage = error.data.message;
                    });
            };
        }],
        controllerAs: 'eventTableCtrl',
        templateUrl: 'js/event/event-table/event-table-template.html'
    };
});