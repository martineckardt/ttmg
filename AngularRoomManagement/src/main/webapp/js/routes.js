/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg').config(
    function($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'js/home/index.html'
        })
        // ROOMS
        .when('/rooms', {
            templateUrl: 'js/list-rooms/index.html',
            controller: 'listRoomsController',
            controllerAs:  'listRoomsCtrl'
        })
        .when('/rooms/create', {
            templateUrl: 'js/create-room/index.html'
        })
        // TUTORS
        .when('/tutors', {
                templateUrl: 'js/list-tutors/index.html',
                controller: 'listTutorsController',
                controllerAs:  'listTutorsCtrl'
            })
        .otherwise(
            { redirectTo: '/' }
        )
        console.log('routes defined')
    }
);