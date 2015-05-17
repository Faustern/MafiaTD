(function() {
    "use strict";

    angular.module("mafia")
        .constant('view', 'js/views/')
        .config(function ($stateProvider, $urlRouterProvider, view) {

            var resolvePath = function(type, filename) {
                switch (type) {
                    case 'base':
                        return view + filename + ".html";
                    case 'partial':
                        return view + 'partial/' + filename + '.html';
                    case 'partialModal':
                        return view + 'partial/modal/' + filename + '.html';
                }
            };

            $stateProvider
                .state('admin', {
                    url: '/admin',
                    views: {
                        '@': {
                            templateUrl: resolvePath('base', 'admin')
                        },
                        'game@admin': {
                            templateUrl: resolvePath('partial', 'game'),
                            controller: 'gameController'
                        },
                        'games@admin': {
                            templateUrl: resolvePath('partial', 'games'),
                            controller: 'gamesController'
                        },
                        'members@admin': {
                            templateUrl: resolvePath('partial', 'members'),
                            controller: 'membersController'
                        },
                        'rating@admin': {
                            templateUrl: resolvePath('partial', 'rating'),
                            controller: 'ratingController'
                        },
                        'statistics@admin': {
                            templateUrl: resolvePath('partial', 'statistics'),
                            controller: 'statisticsController'
                        },
                        'gameTable@admin': {
                            templateUrl: resolvePath('partial', 'gameTable')
                        }
                    }
                })
                .state('test', {
                    url: '/index',
                    views: {
                        '@': {
                            templateUrl: resolvePath('base', 'index')
                        }
                    }
                });
            $urlRouterProvider.otherwise('/admin');
        });
})();


