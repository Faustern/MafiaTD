angular.module('customDirectives',[])
    .directive('autoComplete', function($timeout) {
        return function(scope, iElement, iAttrs) {
            scope.$watch(iAttrs.uiItems, function() {
                iElement.autocomplete({
                    source: scope[iAttrs.uiItems],
                    select: function() {
                        $timeout(function() {
                            iElement.trigger('input');
                        }, 0);
                    }
                });
            }, true);
        };
    }).directive('datepickerPopup', function (){
        return {
            restrict: 'EAC',
            require: 'ngModel',
            link: function(scope, element, attr, controller) {
                //remove the default formatter from the input directive to prevent conflict
                controller.$formatters.shift();
            }
        }
    });