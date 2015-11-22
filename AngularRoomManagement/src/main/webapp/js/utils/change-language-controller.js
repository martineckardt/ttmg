/**
 * Created by Martin Eckardt on 21.11.2015.
 * A controller for the two buttons to switch between the languages.
 */

angular.module('ttmg.controllers').controller('changeLanguageController', ['$scope', '$translate', function ($scope, $translate) {

    $scope.currentLanguage = 'de';

    //create a new room
    $scope.changeLanguage = function (langKey) {
        console.log("change language to " + langKey);
        $scope.currentLanguage = langKey;
        $translate.use(langKey);
    };

}]);
