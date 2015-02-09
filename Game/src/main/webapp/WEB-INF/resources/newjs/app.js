angular.module('mafia', [
    'ngRoute',
    'customServices',
    'mafia.controllers'
])
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when('/game', {
                    templateUrl: '/resources/newjs/views/game.html'})
                .otherwise({
                    templateUrl: '/resources/newjs/views/game.html'})
    }]);
