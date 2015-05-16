(function() {
    "use strict";

    angular.module("mafia")
        .constant('view', 'js/views/')
        .config(function ($stateProvider, $urlRouterProvider, view) {

            var resolvePath = function(type, filename) {
                switch (type) {
                    case "base":
                        return view + filename + ".html";
                    case "partial":
                        return view + "partial/" + filename + ".html";
                    case "partialModal":
                        return view + "partial/modal/" + filename + ".html";
                }
            };

            $stateProvider
                .state("admin", {
                    url: "/admin",
                    views: {
                        '@': {
                            templateUrl: resolvePath("base","admin")
                        },
                        'game@admin': {
                            templateUrl: resolvePath("partial", "game")
                        },
                        'games@admin': {
                            templateUrl: resolvePath("partial","games")
                        },
                        'members@admin': {
                            templateUrl: resolvePath("partial","members")
                        },
                        'rating@admin': {
                            templateUrl: resolvePath("partial","rating")
                        },
                        'statistics@admin': {
                            templateUrl: resolvePath("partial","statistics")
                        }
                    }
                })
                .state("test", {
                    url: "/index",
                    views: {
                        '@': {
                            templateUrl: resolvePath("base","index")
                        }
                    }
                });
            $urlRouterProvider.otherwise("/admin");
        });
})();


