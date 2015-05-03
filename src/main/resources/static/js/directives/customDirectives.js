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
});