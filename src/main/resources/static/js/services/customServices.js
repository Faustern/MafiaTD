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
    }]);

