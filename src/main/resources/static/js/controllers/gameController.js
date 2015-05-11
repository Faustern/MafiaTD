angular.module('mafia.controllers',['customDirectives','timer','ui.bootstrap'])
    .controller('gameController', function($scope, $modal, $log, httpService) {

        $scope.range = function (count, defaultValue) {
            var array = Array.apply(null, new Array(count))
            switch(typeof defaultValue) {
                case "undefined":
                    return array.map(String.prototype.valueOf, "");
                case "string":
                    return array.map(String.prototype.valueOf, defaultValue);
                case "object":
                    return array.map(Object.prototype.valueOf, defaultValue);
            }
        };

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
                role: 0,
                life: 0,
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
        });

        httpService.get('lives', {}, function(result) {
            $scope.LIVES = result;
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

        $scope.showRating = function(season) {
            httpService.get("rating", {season: season},
                function(results) {
                    $scope.ratingStrings = [];
                    angular.forEach(results, function (result, index) {
                        var games = result.games;
                        var rating = result.rating.toString().match('^[0-9]+[.]*[0-9]{0,2}')[0];
                        var prefix = games > 10 ? index + 1 + '. ' : '';
                        $scope.ratingStrings.push(
                            prefix+'@'+result.vkontakte+' ('+result.nickname+')  '+rating+'% ('+games+')');
                    });
                }
            );
        };

        $scope.addNewPlayer = function(player) {
            httpService.post("member", player,
                function() {
                    $scope.ALL_PLAYERS.push(player);
                    $scope.openInfoModal("Player successfully added!");
                },
                function(error) {
                    $scope.openErrorModal("player FAILED to add;");
                }
            );
        };

        $scope.validation = function() {
            $scope.uniqueNicknames = [];
            $scope.uniqueNicknamesNumbers = [];
            $scope.duplicates = [];
            $scope.duplicateNumbers = [];

            angular.forEach($scope.game.players, function (player, number) {
                $scope.checkNickname(player.member, number);
            });

            angular.forEach($scope.duplicates, function (duplicate, index) {
                $scope.validationErrors.push('Players ' + $scope.duplicateNumbers[index] + ' have the same nickname: "' +
                duplicate + '"');
            });
            $scope.checkMaster($scope.game.master);
        };

        $scope.calculateRating = function() {
            if ($scope.validationErrors.length == 0) {
                httpService.post("rating", $scope.game,
                    function (results) {
                        angular.forEach(results, function (result, index) {
                            $scope.rating[index] = result;
                        });
                    }
                );
            } else {
                $scope.openErrorModal($scope.validationErrors);
                $scope.validationErrors = [];
            }
        };

        $scope.saveGame = function() {
            $scope.validation();
            if ($scope.validationErrors.length == 0) {
                httpService.post("game", $scope.game,
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

        $scope.openInfoModal = function(message) {
            $scope.openMessageModal(message, 'info');
        };

        $scope.openWarningModal = function(message) {
            $scope.openMessageModal(message, 'warning');
        };

        $scope.openErrorModal = function(message) {
            $scope.openMessageModal(message, 'error');
        };

        $scope.openMessageModal = function(message, level) {
            $modal.open({
                templateUrl: 'js/views/partial/modal/' + level + '.html',
                controller: 'messageModalController',
                resolve: {
                    message: function () {
                        return message;
                    }
                }
            });
        };

        $scope.validationErrors = [];

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

        $scope.items = ['item1', 'item2', 'item3'];

        $scope.open = function (size) {

            var modalInstance = $modal.open({
                templateUrl: 'js/views/modal.html',
                controller: 'modalController',
                size: size,
                resolve: {
                    items: function () {
                        return $scope.items;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $scope.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };


    });
