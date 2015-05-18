angular.module('admin.controllers')
    .controller('gameAccusationController', function ($scope) {

        $scope.accusations = [];
        $scope.addAccusation = function(){
            $scope.accusations.push({ accuse: $scope.accuse, accused: $scope.accused});
            $scope.accused = "";
            $scope.accuse = "";
        };
        $scope.removeAccusation = function(){
            $scope.accusations = $scope.accusations.slice(0, $scope.accusations.length - 1);
        };
        $scope.clearVoting = function(){
            $scope.accusations = [];
        };

    });
