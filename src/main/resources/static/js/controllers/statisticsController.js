angular.module('admin.controllers')
    .controller('statisticsController', function ($scope, httpService, gameService, rangeService) {

        gameService.parameters.then(function(result) {
            $scope.params = result;
            $scope.ALL_MEMBERS = result.ALL_MEMBERS;
            $scope.PLAYERS_AMOUNT = result.PLAYERS_AMOUNT;
            $scope.SEASONS = result.SEASONS;
            $scope.ROLES = result.ROLES;
            $scope.POSITION_ROLES = ['ALL'].concat(result.ROLES);
             $scope.positionRole = $scope.POSITION_ROLES[0];
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
            $scope.statistics.positions['ALL'] = [[], []];
            angular.forEach($scope.ROLES , function (role, index) {
                $scope.statistics.positions[role] = [[], []];
            });

            angular.forEach(rangeService.range($scope.PLAYERS_AMOUNT) , function (item, position) {
                $scope.statistics.positions['ALL'][0].push(positionsStatistics.games[position +1]);
                $scope.statistics.positions['ALL'][1].push(positionsStatistics.winningGames[position + 1]);
                angular.forEach($scope.ROLES , function (role, index) {
                    $scope.statistics.positions[role][0].push(positionsStatistics.roleGames[role][position +1]);
                    $scope.statistics.positions[role][1].push(positionsStatistics.roleWinningGames[role][position +1])
                });
                $scope.statistics.positions.labels.push(position + 1);
            });
            $scope.positionRole == 'ALL' ? changePositionData() : $scope.positionRole = 'ALL';
        };

        var showRolesStatistics = function(rolesStatistics) {
            angular.forEach($scope.ROLES , function (role, index) {
                $scope.statistics.roles.data[0].push(rolesStatistics.games[role]);
                $scope.statistics.roles.data[1].push(rolesStatistics.winningGames[role]);
                $scope.statistics.roles.labels.push(role)
            });
        };

        var showLivesStatistics = function(livesStatistics) {
            angular.forEach($scope.LIVES , function (life, index) {
                $scope.statistics.lives.data[0].push(livesStatistics.games[life]);
                $scope.statistics.lives.data[1].push(livesStatistics.winningGames[life]);
                $scope.statistics.lives.labels.push(life)
            });
        };

        var changePositionData = function() {
            $scope.statistics.positions.data = $scope.statistics.positions[$scope.positionRole];
        };

        $scope.$watch('positionRole', function() {
            changePositionData();
        });

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
