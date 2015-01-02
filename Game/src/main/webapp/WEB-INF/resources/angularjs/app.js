angular.module('mafia', [
    'ngRoute',
    'mafiaControllers'
])
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when('/game', {
                    templateUrl: '/resources/angularjs/views/game.html'})
                .when('/', {
                    templateUrl: '/resources/angularjs/views/game.html'})
                .otherwise({
                    templateUrl: '/resources/angularjs/views/game.html'})
    }]);
