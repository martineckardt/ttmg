/**
 * Created by Martin Eckardt on 28.10.2015.
 * Controller for the detail view of one centuria tat displays the properties and the time table.
 */

angular.module('ttmg.controllers').controller('viewCenturiaController', ['$scope', '$routeParams', 'CenturiaResourceFactory', 'EventResourceFactory', function ($scope, $routeParams, CenturiaResourceFactory, EventResourceFactory) {

    // Route parameters
    var centuriaId = $routeParams.id;
    console.log('viewCenturiaController for Centuria ' + centuriaId + ' started');

    // Set up scope model
    $scope.model = {
        centuria: CenturiaResourceFactory.get({centuriaId: centuriaId}),
        events: EventResourceFactory.query({centuriaId: centuriaId})
    };
}]);