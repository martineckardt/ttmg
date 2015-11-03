/**
 * Created by U519643 on 28.10.2015.
 */

angular.module('ttmg.services').service('tutorService', ['$http', function ($http) {

    console.log('tutorService initialized')

    /**
     * Return all tutors using an asynchronous REST call with promise.
     * @returns {HttpPromise}.
     */
    this.listTutorsWithPromise = function () {
        return $http.get('rest/tutors');
    };

    /**
     * Saves a given tutor using an asynchronous REST call with promise.
     * @param tutor The tutor to be saved.
     * @returns {HttpPromise}.
     */
    this.saveTutorWithPromise = function (tutor) {
        return $http.put('rest/tutors', tutor);
    };

    /**
     * Deletes the given tutor using an asynchronous REST call with promise.
     * @param tutor The tutor to be deleted.
     * @returns {HttpPromise}.
     */
    this.deleteTutorWithPromise = function (tutor) {
        return $http.delete('rest/tutors/' + tutor.id);
    }

}]);