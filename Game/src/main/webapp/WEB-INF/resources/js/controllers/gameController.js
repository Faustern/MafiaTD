angular.module('mafia.controllers',['customDirectives','timer','ui.bootstrap'])
    .controller('gameController', function($scope, httpService) {

        $scope.PLAYERS_AMOUNT = 10;
        $scope.MAX_FOULS_AMOUNT = 4;
        $scope.ALL_PLAYERS = [];
        $scope.RESULTS = ["clear city win","city win","mafia win","clear mafia win"];
        $scope.ROLES = ["Don", "Mafia", "Citizen", "Sheriff"];
        $scope.LIVES = ["killed 1 night", "killed 2 night", "killed 3 night", "killed 4 night", "killed 5 plus night",
            "away 0 day", "away 1 day", "away 2 day", "away 3 day", "away 4 day", "away 5 plus day", "not away"];
        $scope.SEASONS = ["All seasons","Winter 13/14","Spring 14","Autumn 14","Winter 14/15"];
        $scope.nicknames = new Array($scope.PLAYERS_AMOUNT);
        $scope.roles = new Array($scope.PLAYERS_AMOUNT);
        $scope.lives = new Array($scope.PLAYERS_AMOUNT);
        $scope.bestVoices = new Array($scope.PLAYERS_AMOUNT);
        $scope.finalDecisions = new Array($scope.PLAYERS_AMOUNT);
        $scope.fouls = new Array($scope.PLAYERS_AMOUNT);
        $scope.rating = new Array($scope.PLAYERS_AMOUNT);
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

        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.gameDate.isOpened = true;
        };

        //For Debug
        $.each($scope.nicknames, function (index) {
            $scope.roles[index] = 1;
            $scope.lives[index] = 1;
            $scope.bestVoices[index] = 0;
            $scope.finalDecisions[index] = 0;
            $scope.fouls[index] = 0;
        });

        $scope.timerRunning = false;

        $scope.countdown = 60;
        $scope.time = 60;
        $scope.startTimer = function (time){
            $scope.$broadcast('timer-set-countdown', time);
            $scope.$broadcast('timer-start');
        };

        $scope.stopTimer = function (){
            $scope.$broadcast('timer-stop');
        };

        $scope.$on('timer-stopped', function (event, data){
            if (data.millis == 0) {
                alert('Time is out');
            }
        });

        $scope.range = function (count) {
            return new Array(count);
        };

        httpService.get('getAllPlayers', {}, function(result) {
            $scope.ALL_PLAYERS = result;
        });

        $scope.showRating = function(season) {
            httpService.get("showPlayersRating", {season: season},
                function(results) {
                    $scope.ratingStrings = [];
                    angular.forEach(results, function (result, index) {
                        var games = result.gamesPlayed.length;
                        var player = result.player;
                        var rating = result.rating.toString().match('^[0-9]+[.]*[0-9]{0,2}')[0];
                        if (games > 0) {
                            var prefix = games > 10 ? '' : index + 1 + '. ';
                            $scope.ratingStrings.push(
                                prefix+'@'+player.vkontakte+' ('+player.nickname+')  '+rating+'% ('+games+')');
                        }
                    });
                }
            );
        };

        $scope.addNewPlayer = function(player) {
            httpService.post("addPlayerToDB", {nickname: player},
                function(results) {
                    $scope.ALL_PLAYERS.push(player);
                    alert("Player successfully added!");
                },
                function(error) {
                    alert("player FAILED to add;");
                }
            );
        };

        $scope.calculateRating = function() {
            httpService.get("calculateRating",
                {
                    season: $scope.season,
                    date: $scope.gameDate.data,
                    result: $scope.result,
                    masterNickname: $scope.master,
                    nickNames: $scope.nicknames,
                    roles: $scope.roles,
                    lives: $scope.lives,
                    bestVoices: $scope.bestVoices,
                    finalDecisions: $scope.finalDecisions,
                    fouls: $scope.fouls
                },
                function(results) {
                    angular.forEach(results, function (result, index) {
                        $scope.rating[index] = result.totalRating;
                    });
                }
            );
        };

        $scope.saveGame = function() {
            httpService.post("saveGameIntoDB",
                {
                    season: $scope.season,
                    date: $scope.gameDate.data,
                    result: $scope.result,
                    masterNickname: $scope.master,
                    nickNames: $scope.nicknames,
                    roles: $scope.roles,
                    lives: $scope.lives,
                    bestVoices: $scope.bestVoices,
                    finalDecisions: $scope.finalDecisions,
                    fouls: $scope.fouls
                },
                function(results) {
                    alert('game successfully saved!');
                },
                function(error) {
                    alert('game FAILED to save');
                }
            );
        };

    });