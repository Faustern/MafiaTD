angular.module('customServices',[])
    .factory('httpService', ['$http', function($http) {

        var send = function(http, callback, errorCallback) {
            $('#loadingWidget').show();
            $http(http).success(function (data) {
                $('#loadingWidget').hide();
                callback(data);
            }).error(function (error) {
                errorCallback(error);
                $('#loadingWidget').hide();
            })
        };

        return {
            get: function(url, params, callback, errorCallback) {
                if (params == null) {
                    throw "Config object should be provided";
                }
                send({url: url, method: "GET", params: params}, callback, errorCallback);
            },
            post: function(url, data, callback, errorCallback) {
                if (data == null) {
                    throw "Config object should be provided";
                }
                send({url: url, method: "POST", data: data}, callback, errorCallback);
            },
            delete: function(url, params, callback, errorCallback) {
                if (params == null) {
                    throw "Config object should be provided";
                }
                send({url: url, method: "DELETE", params: params}, callback, errorCallback);
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
            var array = Array.apply(null, new Array(count));
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
            range: function(size) {
                return range(size);
            },
            intRange: function (size) {
                var array = range(size + 1);
                angular.forEach(array, function (result, index) {
                    array[index] = index;
                });
                return array;
            }
        }
    })
    .factory('gameValidationService', function() {

        var ALL_MEMBERS = [];
        var uniqueNicknames = [];

        var checkNicknames = function (players) {

            var validationErrors = [];
            var uniqueNicknamesNumbers = [];
            var duplicates = [];
            var duplicateNumbers = [];

            angular.forEach(players, function (player, number) {
                var nickname = player.member.nickname;
                number++;
                if (nickname == null || nickname == "") {
                    validationErrors.push('Nickname field for player ' + number + ' is empty');
                } else if (ALL_MEMBERS.indexOf(nickname) == -1) {
                    validationErrors.push('Player number ' + number + ': there are no no player with nickname "' +
                    nickname + '"');
                } else if (uniqueNicknames.indexOf(nickname) != -1) {
                    if (duplicates.indexOf(nickname) == -1) {
                        duplicates.push(nickname);
                        duplicateNumbers.push([uniqueNicknamesNumbers[uniqueNicknames.indexOf(nickname)], number]);
                    } else {
                        duplicateNumbers[duplicates.indexOf(nickname)].push(number);
                    }
                } else {
                    uniqueNicknames.push(nickname);
                    uniqueNicknamesNumbers.push(number);
                }

            });

            angular.forEach(duplicates, function (duplicate, index) {
                validationErrors.push('Players ' + duplicateNumbers[index] + ' have the same nickname: "' +
                duplicate + '"');
            });

            return validationErrors;
        };

        var checkMaster = function (master) {
            var validationErrors = [];
            if (master == null || master == "") {
                validationErrors.push('Master field is empty');
            } else if (ALL_MEMBERS.indexOf(master) == -1) {
                validationErrors.push('Master field: There are no player with nickname "' + master + '"!');
            } else if (uniqueNicknames.indexOf(master) != -1) {
                validationErrors.push('Master field: "' + master + '" could not be player  number ' +
                (uniqueNicknames.indexOf(master) + 1) + ' and master');
            }
            return validationErrors;
        };

        return {
            validate: function (game, members) {
                ALL_MEMBERS = members;
                uniqueNicknames = [];
                return checkNicknames(game.players)
                    .concat(checkMaster(game.master));
            }
        }
    })
    .factory('gameService', function($q, $http) {

        var results = $q.defer();
        var seasons = $q.defer();
        var members = $q.defer();
        var roles = $q.defer();
        var lives = $q.defer();

        $http.get('results').then(function(result) {results.resolve(result.data)});
        $http.get('seasons').then(function(result) {seasons.resolve(result.data)});
        $http.get('member-nicknames').then(function(result) {members.resolve(result.data)});
        $http.get('roles').then(function(result) {roles.resolve(result.data)});
        $http.get('lives').then(function(result) {lives.resolve(result.data)});

        return {
            parameters: $q.all([results.promise, seasons.promise, members.promise, roles.promise, lives.promise]).then(
                    function(result) {
                        return {
                            RESULTS: result[0],
                            SEASONS: result[1],
                            ALL_MEMBERS: result[2],
                            ROLES: result[3],
                            LIVES: result[4],
                            PLAYERS_AMOUNT: 10,
                            MAX_FOULS_AMOUNT: 4
                        }
                    }
            )
        }

    });
