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
                .when('/index', {
                    templateUrl: '/resources/js/views/index.html'})
                .otherwise({
                    templateUrl: '/resources/js/views/game.html'})
    }]);
