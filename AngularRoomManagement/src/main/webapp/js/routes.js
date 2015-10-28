/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg').config(
    function($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'js/home/index.html'
        })
        .when('/rooms', {
            templateUrl: 'js/list-rooms/index.html'
        })
        .when('/rooms/create', {
            templateUrl: 'js/create-room/index.html'
        })
        .otherwise(
            { redirectTo: '/' }
        )
        console.log('routes defined')
    }
);