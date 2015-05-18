angular.module('admin.controllers')
    .controller('gameTimerController', function ($scope, modalService) {

        $scope.openWarningModal = modalService.openWarningModal;
        $scope.timerRunning = false;

        $scope.countdown = 60;
        $scope.time = 60;
        $scope.startTimer = function (time){
            $scope.$broadcast('timer-set-countdown', time);
            $scope.$broadcast('timer-start');
            $scope.timerRunning  = true;
        };

        $scope.stopTimer = function (){
            $scope.$broadcast('timer-stop');
        };

        $scope.$on('timer-stopped', function (event, data){
            $scope.timerRunning = false;
            if (data.millis == 0) {
                $scope.openWarningModal('Time Is out');
            }
        });

    });
