angular.module('admin.controllers')
        .controller('gamesController', function ($scope, $modal, httpService, modalService, rangeService,
                                                 gameValidationService) {

            httpService.get('seasons', {}, function(result) {
                $scope.SEASONS = result;
                $scope.season = $scope.SEASONS[$scope.SEASONS.length - 1];
            });

            $scope.masterGame = false;
            $scope.openInfoModal = modalService.openInfoModal;
            $scope.openWarningModal = modalService.openWarningModal;
            $scope.openErrorModal = modalService.openErrorModal;
            $scope.intRange = rangeService.intRange;
            $scope.validation = gameValidationService.validate;


            $scope.PLAYERS_AMOUNT = 10;
            $scope.MAX_FOULS_AMOUNT = 4;
            $scope.games = [];
            $scope.gameDate = [];

            $scope.getGames = function(season) {
                $scope.games = [];
                httpService.get('games', {season: season}, function (results) {
                    angular.forEach(results, function (result, index) {
                        $scope.games[index] = result;
                        $scope.gameDate[index] = {
                            data: $.datepicker.formatDate("yy/mm/dd", new Date()),
                            format: 'yyyy/MM/dd',
                            isOpened: false,
                            options: {
                                'starting-day': 1,
                                'show-weeks': false
                            }
                        }
                    });
                });
            };

            $scope.openDatePicker = function ($event, index) {
                $event.preventDefault();
                $event.stopPropagation();
                $scope.gameDate[index].isOpened = true;
            };

            httpService.get('members', {}, function (result) {
                $scope.ALL_MEMBERS = result;
            });

            httpService.get('results', {}, function (result) {
                $scope.RESULTS = result;
            });

            httpService.get('seasons', {}, function (result) {
                $scope.SEASONS = result;
            });

            httpService.get('roles', {}, function (result) {
                $scope.ROLES = result;
            });

            httpService.get('lives', {}, function (result) {
                $scope.LIVES = result;
            });

            $scope.showPlayers = function (game) {
                game.players == undefined || game.players.length == 0 ?
                    httpService.get('players', {gameId: game.id}, function (results) {
                        game.players = results;
                    }) : game.players = [];
            };

            $scope.updateGame = function (game) {
                var validationErrors = $scope.validation(game, $scope.ALL_MEMBERS);
                if (validationErrors.length == 0) {
                    httpService.post("game", game,
                        function () {
                            $scope.openInfoModal('game successfully saved!');
                        },
                        function (error) {
                            $scope.openErrorModal('game FAILED to save');
                        }
                    );
                } else {
                    $scope.openErrorModal(validationErrors);
                }
            };

            $scope.deleteGame = function (id) {
                httpService.delete("game", {gameId: id},
                    function () {
                        angular.forEach($scope.games, function (game, index) {
                            if (game.id == id) {
                                $scope.games.splice(index, 1);
                            }
                        });
                        $scope.openInfoModal('game successfully deleted!');
                    },
                    function (error) {
                        $scope.openErrorModal('game FAILED to delete');
                    }
                );
            };

        });

