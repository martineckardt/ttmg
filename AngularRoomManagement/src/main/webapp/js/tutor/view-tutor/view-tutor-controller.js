/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('viewTutorController', ['$scope', '$routeParams', 'TutorFactory', function ($scope, $routeParams, TutorFactory) {

    console.log('viewTutorController for Tutor ' + $routeParams.id + ' started');

    $scope.model = [];

    $scope.model.tutor = TutorFactory.get({id: $routeParams.id});
}]);