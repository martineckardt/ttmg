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
                templateUrl: 'js/room/list-rooms/index.html',
            controller: 'listRoomsController',
            controllerAs:  'listRoomsCtrl'
        })
        .when('/rooms/create', {
                templateUrl: 'js/room/create-room/index.html'
        })
        // TUTORS
        .when('/tutors', {
                templateUrl: 'js/tutor/list-tutors/index.html',
                controller: 'listTutorsController',
                controllerAs:  'listTutorsCtrl'
            })
            // CENTURIAS
            .when('/centurias', {
                templateUrl: 'js/century/list-centurias/index.html',
                controller: 'listCenturiasController',
                controllerAs: 'listCenturiasCtrl'
            })
            // COURSES
            .when('/courses', {
                templateUrl: 'js/course/list-courses/index.html',
                controller: 'listCoursesController',
                controllerAs: 'listCoursesCtrl'
            })
            .when('/courses/:id', {
                templateUrl: 'js/course/show-course/index.html',
                controller: 'showCourseController',
                controllerAs: 'showCourseCtrl'
            })
        .otherwise(
            { redirectTo: '/' }
        )
        console.log('routes defined')
    }
);