angular.module('main.controllers')
        .controller('modalController', function ($scope, $modalInstance, items) {
            $scope.items = items;
            $scope.selected = {
                item: $scope.items[0]
            };

            $scope.ok = function () {
                $modalInstance.close($scope.selected.item);
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        })
        .controller('messageModalController', function ($scope, $modalInstance, message) {
            if (typeof message === 'string') {
                message = [message];
            }
            $scope.messages = message;

            $scope.ok = function () {
                $modalInstance.close();
            };
        });

