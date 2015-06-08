angular.module('admin.controllers')
    .controller('statisticsController', function ($scope, httpService, gameService, rangeService) {

        gameService.parameters.then(function(result) {
            $scope.params = result;
            $scope.ALL_MEMBERS = result.ALL_MEMBERS;
            $scope.PLAYERS_AMOUNT = result.PLAYERS_AMOUNT;
            $scope.SEASONS = result.SEASONS;
            $scope.ROLES = result.ROLES;
            $scope.LIVES = result.LIVES;
            $scope.season = $scope.SEASONS[$scope.SEASONS.length - 1];
        });

        $scope.showStatistics = function(nickname, season) {
            resetStatistics();
            httpService.get("statistics", {nickname: nickname, season: season},
                function(memberStatistics) {
                    showPositionsStatistics(memberStatistics.positions);
                    showRolesStatistics(memberStatistics.roles);
                    showLivesStatistics(memberStatistics.lives);
                });
        };

        var showPositionsStatistics = function(positionsStatistics) {
            angular.forEach(rangeService.range($scope.PLAYERS_AMOUNT) , function (item, position) {
                $scope.statistics.positions.data[0]
                    .push(positionsStatistics.games[position +1] ? positionsStatistics.games[position + 1] : 0);
                $scope.statistics.positions.data[1]
                    .push(positionsStatistics.winningGames[position + 1] ? positionsStatistics.winningGames[position + 1] : 0);
                $scope.statistics.positions.labels.push(position + 1)
            });
        };

        var showRolesStatistics = function(rolesStatistics) {
            angular.forEach($scope.ROLES , function (role, index) {
                $scope.statistics.roles.data[0]
                    .push(rolesStatistics.games[role] ? rolesStatistics.games[role] : 0);
                $scope.statistics.roles.data[1]
                    .push(rolesStatistics.winningGames[role] ? rolesStatistics.winningGames[role] : 0);
                $scope.statistics.roles.labels.push(role)
            });
        };

        var showLivesStatistics = function(livesStatistics) {
            angular.forEach($scope.LIVES , function (life, index) {
                $scope.statistics.lives.data[0]
                    .push(livesStatistics.games[life] ? livesStatistics.games[life] : 0);
                $scope.statistics.lives.data[1]
                    .push(livesStatistics.winningGames[life] ? livesStatistics.winningGames[life] : 0);
                $scope.statistics.lives.labels.push(life)
            });
        };

        var resetStatistics = function() {
            $scope.statistics = {
                positions: {
                    data: [[], []],
                    labels: []
                },
                roles: {
                    data: [[], []],
                    labels: []
                },
                lives: {
                    data: [[], []],
                    labels: []
                }
            };
        };

        resetStatistics();

    });
