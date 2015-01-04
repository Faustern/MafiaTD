angular.module('mafia', [
    'ngRoute',
    'customServices',
    'mafia.controllers'
])
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when('/game', {
                    templateUrl: '/resources/js/views/game.html'})
                .when('/', {
                    templateUrl: '/resources/js/views/game.html'})
                .otherwise({
                    templateUrl: '/resources/js/views/game.html'})
    }]);
