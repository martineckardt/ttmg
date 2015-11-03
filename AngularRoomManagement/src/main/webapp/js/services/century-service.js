/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg.services').service('centuryService', ['$http', function ($http) {

    console.log('centuryService initialized')

    /**
     * Return all centuries using an asynchronous REST call with promise.
     * @returns {HttpPromise}.
     */
    this.listCenturiasWithPromise = function () {
        return $http.get('rest/centurias');
    };

    /**
     * Saves a given century using an asynchronous REST call with promise.
     * @param century The century to be saved.
     * @returns {HttpPromise}.
     */
    this.saveCenturyWithPromise = function (century) {
        return $http.put('rest/centurias', century);
    };

    /**
     * Deletes the given century using an asynchronous REST call with promise.
     * @param century The century to be deleted.
     * @returns {HttpPromise}.
     */
    this.deleteCenturyWithPromise = function (century) {
        return $http.delete('rest/centurias/' + century.id);
    }

}]);