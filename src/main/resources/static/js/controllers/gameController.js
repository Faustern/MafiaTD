angular.module('admin.controllers')
    .controller('gameController', function($scope, $modal, $log, httpService, modalService, rangeService,
                                           gameValidationService, gameService) {

        $scope.masterGame = true;
        $scope.intRange = rangeService.intRange;

        gameService.parameters.then(function(result) {
            $scope.params = result;
            $scope.ALL_MEMBERS = result.ALL_MEMBERS;
            $scope.game = {
                season: result.SEASONS[result.SEASONS.length - 1],
                master: "Faust",
                date: new Date(),
                result: result.RESULTS[0],
                players: []
            };

            angular.forEach(rangeService.range(result.PLAYERS_AMOUNT), function (item, index) {
                $scope.game.players.push({
                    member: "",
                    number: index + 1,
                    role: result.ROLES[0],
                    life: result.LIVES[0],
                    bestVoices: 0,
                    finalDecision: 0,
                    fouls: 0
                });
            });
            $scope.rating = rangeService.range(result.PLAYERS_AMOUNT);
        });

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
            var validationErrors = gameValidationService.validate($scope.game, $scope.ALL_MEMBERS);
            if (validationErrors.length == 0) {
                httpService.post("game", $scope.game,
                    function () {
                        modalService.openInfoModal('game successfully saved!');
                    },
                    function (error) {
                        modalService.openErrorModal('game FAILED to save');
                    }
                );
            } else {
                modalService.openErrorModal(validationErrors);
                $scope.validationErrors = [];
            }
        };
    });
