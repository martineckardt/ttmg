/**
 * Created by Martin Eckardt on 28.10.2015.
 */

angular.module('ttmg.controllers').controller('viewCourseController', ['$scope', '$routeParams', 'courseFactory', function ($scope, $routeParams, courseFactory) {

    console.log('viewCourseController for Course ' + $routeParams.id + ' started');

    $scope.model = [];

    $scope.model.room = courseFactory.get({id: roomId});
}]);