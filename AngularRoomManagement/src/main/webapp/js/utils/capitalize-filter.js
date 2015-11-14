/**
 * Created by Martin Eckardt on 14.11.2015.
 * Source: http://stackoverflow.com/questions/30207272/capitalize-the-first-letter-of-string-in-angularjs
 */

angular.module('ttmg.utils').filter('capitalize', function () {
    return function (input) {
        console.log(input);
        return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
    }
});
