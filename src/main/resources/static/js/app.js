angular.module('mafia', [
    'ngRoute',
    'customServices',
    'main.controllers',
    'mafia.controllers'
])
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when('/game', {
                    templateUrl: 'js/views/game.html'})
                .when('/new', {
                    templateUrl: 'newjs/views/game.html'})
                .when('/index', {
                    templateUrl: 'js/views/index.html'})
                .otherwise({
                    templateUrl: 'js/views/game.html'})
    }]);
