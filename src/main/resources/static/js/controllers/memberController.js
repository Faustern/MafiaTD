angular.module('admin.controllers')
    .controller('membersController', function ($scope, httpService, modalService) {

        $scope.openInfoModal = modalService.openInfoModal;
        $scope.openWarningModal = modalService.openWarningModal;
        $scope.openErrorModal = modalService.openErrorModal;

        $scope.addNewPlayer = function(player) {
            httpService.post("member", player,
                function() {
                //    $scope.ALL_PLAYERS.push(player);
                    $scope.openInfoModal("Player successfully added!");
                },
                function(error) {
                    $scope.openErrorModal("player FAILED to add;");
                }
            );
        };
        
    });
