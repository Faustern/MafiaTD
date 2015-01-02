angular.module('mafiaControllers',[])
    .controller('gameController', function($scope) {
        $scope.nicknames = "Faust & Dina";

        $scope.PLAYERS_AMOUNT = 10;

        $scope.MAX_FOULS_AMOUNT = 4;

        $scope.range = function (count) {
            return new Array(count);
        }
    });
