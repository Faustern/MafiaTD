angular.module('customServices',[])
    .factory('httpService', ['$http', function($http) {
        return {
            get: function(url, params, callback, errorCallback) {
                if (params == null) {
                    throw "Config object should be provided";
                }
                $http({
                    url: url,
                    method: "GET",
                    params: params
                }).success(function(data) {
                    callback(data);
                }).error(function(error) {
                    errorCallback(error);
                })
            },
            post: function(url, params, callback, errorCallback) {
                if (params == null) {
                    throw "Config object should be provided";
                }
                $http({
                    url: url,
                    method: "POST",
                    params: params
                }).success(function(data) {
                    callback(data);
                }).error(function(error) {
                    errorCallback(error);
                })
            }
        }
    }]);

