angular.module('admin.controllers')
    .controller('gameController', function($scope, $modal, $log, httpService, modalService, rangeService,
                                           gameValidationService) {

        $scope.intRange = rangeService.intRange;
        $scope.range = rangeService.range;
        $scope.openInfoModal = modalService.openInfoModal;
        $scope.openWarningModal = modalService.openWarningModal;
        $scope.openErrorModal = modalService.openErrorModal;
        $scope.validation = gameValidationService.validate;

        $scope.PLAYERS_AMOUNT = 10;
        $scope.MAX_FOULS_AMOUNT = 4;
        $scope.ratingSeason = 0;

        $scope.game = {
            season: 2,
            master: "Faust",
            date: "2015/01/17",
            result: 0,
            players: []
        };

        angular.forEach($scope.range($scope.PLAYERS_AMOUNT), function (item, index) {
            $scope.game.players.push({
                member: "",
                number: index + 1,
                bestVoices: 0,
                finalDecision: 0,
                fouls: 0
            });
        });
        $scope.rating = $scope.range($scope.PLAYERS_AMOUNT);

        httpService.get('members', {}, function(result) {
            $scope.ALL_PLAYERS = result;
        });

        httpService.get('results', {}, function(result) {
            $scope.RESULTS = result;
        });

        httpService.get('roles', {}, function(result) {
            $scope.ROLES = result;
            angular.forEach($scope.game.players, function (item, index) {
                item.role = $scope.ROLES[0];
            });
        });

        httpService.get('lives', {}, function(result) {
            $scope.LIVES = result;
            angular.forEach($scope.game.players, function (item, index) {
                item.life = $scope.LIVES[0];
            });
        });

        httpService.get('seasons', {}, function(result) {
            $scope.SEASONS = result;
        });

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

        $scope.gameDate = {
            data: $.datepicker.formatDate("yy/mm/dd", new Date()),
            format:'yyyy/MM/dd',
            isOpened: false,
            options: {
                'starting-day': 1,
                'show-weeks': false
            }
        };

        $scope.openDatePicker = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.gameDate.isOpened = true;
        };

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

        $scope.calculateRating = function() {
            httpService.post("rating", $scope.game,
                function (results) {
                    angular.forEach(results, function (result, index) {
                        $scope.rating[index] = result;
                    });
                }
            );
        };

        $scope.saveGame = function() {
            var validationErrors = $scope.validation($scope.game, $scope.ALL_PLAYERS);
            if (validationErrors.length == 0) {
                httpService.post("game", $scope.game,
                    function () {
                        $scope.openInfoModal('game successfully saved!');
                    },
                    function (error) {
                        $scope.openErrorModal('game FAILED to save');
                    }
                );
            } else {
                $scope.openErrorModal(validationErrors);
                $scope.validationErrors = [];
            }
        };
    });
