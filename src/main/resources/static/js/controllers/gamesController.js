angular.module('admin.controllers')
        .controller('gamesController', function ($scope, $modal, httpService, modalService, rangeService,
                                                 gameValidationService, gameService) {

            $scope.masterGame = false;
            $scope.intRange = rangeService.intRange;

            $scope.games = [];
            $scope.gameDate = [];
            $scope.getGames = function(season) {
                $scope.games = [];
                httpService.get('games', {season: season}, function (results) {
                    angular.forEach(results.slice().reverse(), function (result, index) {
                        $scope.games[index] = result;
                        $scope.gameDate[index] = {
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

            gameService.parameters.then(function(result) {
                $scope.params = result;
                $scope.season = result.SEASONS[result.SEASONS.length - 1];
            });

            $scope.showPlayers = function (game) {
                game.players == undefined || game.players.length == 0 ?
                    httpService.get('players', {gameId: game.id}, function (results) {
                        game.players = results;
                    }) : game.players = [];
            };

            $scope.updateGame = function (game) {
                var validationErrors = gameValidationService.validate(game, $scope.params.ALL_MEMBERS);
                if (validationErrors.length == 0) {
                    httpService.post("game", game,
                        function () {
                            modalService.openInfoModal('game successfully saved!');
                        },
                        function (error) {
                            modalService.openErrorModal('game FAILED to save');
                        }
                    );
                } else {
                    modalService.openErrorModal(validationErrors);
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
                        modalService.openInfoModal('game successfully deleted!');
                    },
                    function (error) {
                        modalService.openErrorModal('game FAILED to delete');
                    }
                );
            };

        });

