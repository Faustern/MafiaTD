angular.module('admin.controllers')
    .controller('ratingController', function ($scope, httpService) {

        httpService.get('seasons', {}, function(result) {
            $scope.SEASONS = result;
            $scope.season = $scope.SEASONS[$scope.SEASONS.length - 1];
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

    });
