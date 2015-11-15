/**
 * Created by Martin Eckardt on 28.10.2015.
 *
 */

angular.module('ttmg.controllers').controller('viewCenturiaController', ['$scope', '$routeParams', 'CenturiaFactory', 'EventFactory', function ($scope, $routeParams, CenturiaFactory, EventFactory) {

    // Route parameters
    var centuriaId = $routeParams.id;
    console.log('viewCenturiaController for Centuria ' + centuriaId + ' started');

    // Set up scope model
    $scope.model = {
        centuria: CenturiaFactory.get({id: centuriaId}),
        events: EventFactory.query({centuriaId: centuriaId})
    };
}]);