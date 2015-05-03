angular.module('mafia', [
    'ngRoute',
    'customServices',
    'mafia.controllers',
    'ngFitText'
])
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when('/game', {
                    templateUrl: 'newjs/views/game.html'})
                .otherwise({
                    templateUrl: 'newjs/views/game.html'})
    }]);
