/**
 * Created by Martin Eckardt on 28.10.2015.
 *
 */

angular.module('ttmg.controllers').controller('viewCenturyController', ['$scope', '$routeParams', 'CenturyFactory', 'EventFactory', function ($scope, $routeParams, CenturyFactory, EventFactory) {

    // Route parameters
    var centuryId = $routeParams.id;
    console.log('viewCenturyController for Century ' + centuryId + ' started');

    // Set up scope model
    $scope.model = {
        century: CenturyFactory.get({id: centuryId}),
        events: EventFactory.query({centuryId: centuryId})
    };
}]);