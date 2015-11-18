/**
 * Created by Martin Eckardt on 18.11.2015.
 */

angular.module('ttmg').directive('deleteEntityModal', function () {
    return {
        restrict: 'E',
        scope: {
            entityRessource: '=',
            entityType: '@',
            successHref: '@'
        },
        controller: ['$scope', '$location', function ($scope, $location) {
            console.log("deleteEntityController started");

            this.deleteEntity = function () {
                $scope.entityRessource.$delete({id: $scope.entityRessource.id},
                    function successCallback(data) {
                        console.log("successfully deleted entity");
                        console.log(data);

                        // Redirecting to successHref
                        $location.path($scope.successHref);
                    }, function errorCallback(error) {
                        console.log("failed to delete entity");
                        console.log(error);

                        $scope.deleteEntityErrorMessage = error.data.message;
                    });
            };
        }],
        templateUrl: 'js/common/delete-entity-model-directive/delete-entity-modal-template.html',
        controllerAs: 'deleteEntityModalCtrl'
    };
})