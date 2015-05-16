angular.module('admin.controllers', []);
angular.module('main.controllers', []);
angular.module('mafia', [
    'timer',
    'ui.bootstrap',
    'customDirectives',
    'customServices',
    'main.controllers',
    'admin.controllers',
    'ui.router'
]);

