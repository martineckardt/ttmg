/**
 * Created by Martin Eckardt on 22.11.2015.
 * A directive for a toolbar to select course properties to filter a list of courses.
 */

angular.module('ttmg').directive('courseFilter', function () {
    return {
        restrict: 'E',
        scope: {filter: '='},
        controller: ['$scope', 'TutorResourceFactory', 'COURSE_TYPES', function ($scope, TutorResourceFactory, COURSE_TYPES) {
            $scope.model = {
                tutors: TutorResourceFactory.query(),
                courseTypes: COURSE_TYPES
            };
        }],
        templateUrl: 'js/course/course-filter/course-filter-template.html'
    };
});