angular.module('gameTable.controllers',['customDirectives', 'ui.bootstrap'])
    .controller('gameTableController', function($scope, $modal, httpService, modalService, rangeService) {

        $scope.openInfoModal = modalService.openInfoModal;
        $scope.openWarningModal = modalService.openWarningModal;
        $scope.openErrorModal = modalService.openErrorModal;
        $scope.intRange = rangeService.intRange;


        $scope.PLAYERS_AMOUNT = 10;
        $scope.MAX_FOULS_AMOUNT = 4;
        $scope.games = [];
        $scope.gameDate = [];

        httpService.get('games', {season: 'SPRING_15'}, function(results) {
            angular.forEach(results, function (result, index) {
                $scope.games[index] = result;
                $scope.gameDate[index] = {
                    data: $.datepicker.formatDate("yy/mm/dd", new Date()),
                    format:'yyyy/MM/dd',
                    isOpened: false,
                    options: {
                        'starting-day': 1,
                        'show-weeks': false
                    }
                }
            });
        });

        $scope.openDatePicker = function($event, index) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.gameDate[index].isOpened = true;
        };

        $scope.validationErrors = [];

        httpService.get('members', {}, function(result) {
            $scope.ALL_PLAYERS = result;
        });

        httpService.get('results', {}, function(result) {
            $scope.RESULTS = result;
        });

        httpService.get('seasons', {}, function(result) {
            $scope.SEASONS = result;
        });

        httpService.get('roles', {}, function(result) {
            $scope.ROLES = result;
        });

        httpService.get('lives', {}, function(result) {
            $scope.LIVES = result;
        });

        $scope.showPlayers = function(game) {
            game.players == undefined || game.players.length == 0 ?
                httpService.get('players', {gameId: game.id}, function(results) {
                    game.players = results;
                }) : game.players = [];
        };

        $scope.saveGame = function(game) {
            $scope.validation(game);
            if ($scope.validationErrors.length == 0) {
                httpService.post("game", game,
                    function () {
                        $scope.openInfoModal('game successfully saved!');
                    },
                    function (error) {
                        $scope.openErrorModal('game FAILED to save');
                    }
                );
            } else {
                $scope.openErrorModal($scope.validationErrors);
                $scope.validationErrors = [];
            }
        };

        $scope.deleteGame = function(id) {
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

        $scope.validation = function(game) {
            $scope.uniqueNicknames = [];
            $scope.uniqueNicknamesNumbers = [];
            $scope.duplicates = [];
            $scope.duplicateNumbers = [];

            angular.forEach(game.players, function (player, number) {
                $scope.checkNickname(player.member.nickname, number);
            });

            angular.forEach($scope.duplicates, function (duplicate, index) {
                $scope.validationErrors.push('Players ' + $scope.duplicateNumbers[index] + ' have the same nickname: "' +
                duplicate + '"');
            });
            $scope.checkMaster(game.master);
        };

        $scope.checkNickname = function(nickname , number) {
            number++;
            if (nickname == null || nickname == "") {
                $scope.validationErrors.push('Nickname field for player ' + number + ' is empty');
            } else if ($scope.ALL_PLAYERS.indexOf(nickname) == -1) {
                $scope.validationErrors.push('Player number ' + number + ': there are no no player with nickname "' +
                nickname + '"');
            } else if ($scope.uniqueNicknames.indexOf(nickname) != -1) {
                if ($scope.duplicates.indexOf(nickname) == -1) {
                    $scope.duplicates.push(nickname);
                    $scope.duplicateNumbers.push([$scope.uniqueNicknamesNumbers[$scope.uniqueNicknames.indexOf(nickname)], number]);
                } else {
                    $scope.duplicateNumbers[$scope.duplicates.indexOf(nickname)].push(number);
                }
            } else {
                $scope.uniqueNicknames.push(nickname);
                $scope.uniqueNicknamesNumbers.push(number);
            }
        };

        $scope.checkMaster = function(master){
            if (master == null || master == "") {
                $scope.validationErrors.push('Master field is empty');
            } else if ($scope.ALL_PLAYERS.indexOf(master) == -1) {
                $scope.validationErrors.push('Master field: There are no player with nickname "' + master + '"!');
            } else if ($scope.uniqueNicknames.indexOf(master) != -1) {
                $scope.validationErrors.push('Master field: "' + master + '" could not be player  number ' +
                ($scope.uniqueNicknames.indexOf(master) + 1) + ' and master');
            }
        };
    });
