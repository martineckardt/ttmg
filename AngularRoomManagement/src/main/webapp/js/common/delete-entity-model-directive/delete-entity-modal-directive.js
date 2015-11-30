/**
 * Created by Martin Eckardt on 18.11.2015.
 * Directive that shows a delete-button for an entity. This button opens a modal that asks the user to confirm his wish
 * to delete the entity or cancel the action.
 *
 * If the user confirms the controller tries to delete the entity. On success it switches to the specifies success view.
 * If the deletion fails the message of the backend exception is displayed.
 *
 * The cancel button closes the modal.
 */

angular.module('ttmg').directive('deleteEntityModal', function () {
    return {
        restrict: 'E',
        scope: {
            entityRessource: '=',
            entityType: '@',
            entityName: '@',
            successView: '@',
            buttonType: '@'
        },
        controller: ['$scope', '$location', function ($scope, $location) {
            console.log("deleteEntityController started");

            this.deleteEntity = function () {
                // put together parameters, e.g. courseId
                var params = {};
                params[$scope.entityType + "Id"] = $scope.entityRessource.id;

                $scope.entityRessource.$delete(params,
                    function successCallback(data) {
                        console.log("successfully deleted entity");
                        console.log(data);

                        // Hide the modal
                        $('#deleteEntityModal').modal('hide');

                        // Wait for modal to hide
                        $("#deleteEntityModal").on('hidden.bs.modal', function () {
                            // Redirecting to successHref
                            $location.path($scope.successView);
                            $scope.$apply();
                        });


                    }, function errorCallback(error) {
                        console.log("failed to delete entity");
                        console.log(error);
                        // Show error message
                        $scope.deleteEntityErrorMessage = error.data.message;
                    });
            };
        }],
        templateUrl: 'js/common/delete-entity-model-directive/delete-entity-modal-template.html',
        controllerAs: 'deleteEntityModalCtrl'
    };
})