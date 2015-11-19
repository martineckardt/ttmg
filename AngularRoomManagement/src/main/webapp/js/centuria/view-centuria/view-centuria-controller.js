/**
 * Created by Martin Eckardt on 28.10.2015.
 *
 */

angular.module('ttmg.controllers').controller('viewCenturiaController', ['$scope', '$routeParams', 'CenturiaFactory', function ($scope, $routeParams, CenturiaFactory) {

    // Route parameters
    var centuriaId = $routeParams.id;
    console.log('viewCenturiaController for Centuria ' + centuriaId + ' started');

    // Set up scope model
    $scope.model = {
        centuria: CenturiaFactory.get({centuriaId: centuriaId}),
        events: CenturiaFactory.getEvents({centuriaId: centuriaId})
    };
}]);