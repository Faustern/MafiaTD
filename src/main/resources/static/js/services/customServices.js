angular.module('customServices',[])
    .factory('httpService', ['$http', function($http) {
        return {
            get: function(url, params, callback, errorCallback) {
                if (params == null) {
                    throw "Config object should be provided";
                }
                $('#loadingWidget').show();
                $http({
                    url: url,
                    method: "GET",
                    params: params
                }).success(function(data) {
                    $('#loadingWidget').hide();
                    callback(data);
                }).error(function(error) {
                    errorCallback(error);
                    $('#loadingWidget').hide();
                })
            },
            post: function(url, params, callback, errorCallback) {
                if (params == null) {
                    throw "Config object should be provided";
                }
                $('#loadingWidget').show();
                $http({
                    url: url,
                    method: "POST",
                    data: params
                }).success(function(data) {
                    $('#loadingWidget').hide();
                    callback(data);
                }).error(function(error) {
                    errorCallback(error);
                    $('#loadingWidget').hide();
                })
            },
            delete: function(url, params, callback, errorCallback) {
                if (params == null) {
                    throw "Config object should be provided";
                }
                $('#loadingWidget').show();
                $http({
                    url: url,
                    method: "DELETE",
                    params: params
                }).success(function(data) {
                    $('#loadingWidget').hide();
                    callback(data);
                }).error(function(error) {
                    errorCallback(error);
                    $('#loadingWidget').hide();
                })
            }
        }
    }])
    .factory('modalService', ['$modal', function($modal) {
        var openMessageModal = function(message, level) {
            $modal.open({
                templateUrl: 'js/views/partial/modal/' + level + '.html',
                controller: 'messageModalController',
                resolve: {
                    message: function () {
                        return message;
                    }
                }
            });
        };
        return {
            openInfoModal: function(message) {
                openMessageModal(message, 'info');
            },
            openWarningModal: function(message) {
                openMessageModal(message, 'warning');
            },
            openErrorModal: function(message) {
                openMessageModal(message, 'error');
            }
        }

    }])
    .factory('rangeService', function() {
        var range = function (count, defaultValue) {
            var array = Array.apply(null, new Array(count))
            switch (typeof defaultValue) {
                case "undefined":
                    return array.map(String.prototype.valueOf, "");
                case "string":
                    return array.map(String.prototype.valueOf, defaultValue);
                case "object":
                    return array.map(Object.prototype.valueOf, defaultValue);
            }
        };
        return {
            intRange: function (size) {
                var array = range(size + 1);
                angular.forEach(array, function (result, index) {
                    array[index] = index;
                });
                return array;
            }
        }
    });






