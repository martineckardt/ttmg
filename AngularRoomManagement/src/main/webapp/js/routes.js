/**
 * Created by Martin Eckardt on 28.10.2015.
 *
 * The routes of the application. For every entity there aber basically the following router:
 * list: A list of all instances of the entity
 * create: A form to create a new instance
 * view: A detail view of an instance also showing the schedule
 */

angular.module('ttmg').config(
    function($routeProvider) {
        $routeProvider
            .when('/', {
            templateUrl: 'js/home/home-template.html'
            })
            // ROOMS
            .when('/rooms', {
                templateUrl: 'js/room/list-rooms/list-rooms-template.html',
                controller: 'listRoomsController',
                controllerAs: 'listRoomsCtrl'
            })
            .when('/rooms/create', {
                templateUrl: 'js/room/create-room/create-room-template.html',
                controller: 'createRoomController',
                controllerAs: 'createRoomCtrl'
            })
            .when('/rooms/:id', {
                templateUrl: 'js/room/view-room/view-room-template.html',
                controller: 'viewRoomController',
                controllerAs: 'viewRoomCtrl'
            })
            // TUTORS
            .when('/tutors', {
                templateUrl: 'js/tutor/list-tutors/list-tutors-template.html',
                controller: 'listTutorsController',
                controllerAs: 'listTutorsCtrl'
            })
            .when('/tutors/create', {
                templateUrl: 'js/tutor/create-tutor/create-tutor-template.html',
                controller: 'createTutorController',
                controllerAs: 'createTutorCtrl'
            })
            .when('/tutors/:id', {
                templateUrl: 'js/tutor/view-tutor/view-tutor-template.html',
                controller: 'viewTutorController',
                controllerAs: 'viewTutorCtrl'
            })
            // CENTURIAS
            .when('/centurias', {
                templateUrl: 'js/centuria/list-centurias/list-centurias-template.html',
                controller: 'listCenturiasController',
                controllerAs: 'listCenturiasCtrl'
            })
            .when('/centurias/create', {
                templateUrl: 'js/centuria/create-centuria/create-centuria-template.html',
                controller: 'createCenturiaController',
                controllerAs: 'createCenturiaCtrl'
            })
            .when('/centurias/:id', {
                templateUrl: 'js/centuria/view-centuria/view-centuria-template.html',
                controller: 'viewCenturiaController',
                controllerAs: 'viewCenturiaCtrl'
            })
            // COURSES
            .when('/courses', {
                templateUrl: 'js/course/list-courses/list-courses-template.html',
                controller: 'listCoursesController',
                controllerAs: 'listCoursesCtrl'
            })
            .when('/courses/create', {
                templateUrl: 'js/course/create-course/create-course-template.html',
                controller: 'createCourseController',
                controllerAs: 'createCourseCtrl'
            })
            .when('/courses/:id', {
                templateUrl: 'js/course/view-course/view-course-template.html',
                controller: 'viewCourseController',
                controllerAs: 'viewCourseCtrl'
            })
            .otherwise(
            {redirectTo: '/'}
        )

    }
);