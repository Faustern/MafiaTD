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
            $scope.statistics.positions['ALL'] = [];
            $scope.statistics.positions['ALL'].push(positionsStatistics.games);
            $scope.statistics.positions['ALL'].push(positionsStatistics.winningGames);
            angular.forEach($scope.ROLES , function (role, index) {
                $scope.statistics.positions[role] = [];
                $scope.statistics.positions[role].push(positionsStatistics.roleGames[role]);
                $scope.statistics.positions[role].push(positionsStatistics.roleWinningGames[role])
            });
            $scope.statistics.positions.labels = [1,2,3,4,5,6,7,8,9,10];
            $scope.positionRole == 'ALL' ? changePositionData() : $scope.positionRole = 'ALL';
        };

        var showRolesStatistics = function(rolesStatistics) {
            $scope.statistics.roles.data.push(rolesStatistics.games);
            $scope.statistics.roles.data.push(rolesStatistics.winningGames);
            $scope.statistics.roles.labels = $scope.ROLES;
        };

        var showLivesStatistics = function(livesStatistics) {
            $scope.statistics.lives.data.push(livesStatistics.games);
            $scope.statistics.lives.data.push(livesStatistics.winningGames);
            $scope.statistics.lives.labels = $scope.LIVES;
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
                    data: [],
                    labels: []
                },
                roles: {
                    data: [],
                    labels: []
                },
                lives: {
                    data: [],
                    labels: []
                }
            };
        };

        resetStatistics();

    });
