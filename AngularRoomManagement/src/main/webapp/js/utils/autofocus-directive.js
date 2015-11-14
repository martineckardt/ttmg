/**
 * Created by Martin Eckardt on 14.11.2015.
 * A directive to make autofocus possible with routing.
 * Source: https://gist.github.com/mlynch/dd407b93ed288d499778
 */

angular.module('ttmg.utils')

    .directive('autofocus', ['$timeout', function ($timeout) {
        return {
            restrict: 'A',
            link: function ($scope, $element) {
                $timeout(function () {
                    $element[0].focus();
                });
            }
        }
    }]);
