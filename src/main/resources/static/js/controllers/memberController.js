angular.module('admin.controllers')
    .controller('membersController', function ($scope, httpService, modalService) {

        $scope.members = [];
        $scope.getMembers = function() {
            $scope.members = [];
            httpService.get('members', {}, function (results) {
                $scope.members = results;
            });
        };

        $scope.addMember = function(nickname, vkontakte) {
            httpService.post("member", {nickname: nickname, vkontakte: vkontakte},
                function() {
                    modalService.openInfoModal("Player successfully added!");
                },
                function(error) {
                    modalService.openErrorModal("player FAILED to add;");
                }
            );
        };

        $scope.updateMember = function (member) {
            httpService.post("member", member,
                function () {
                    modalService.openInfoModal('member successfully updated!');
                },
                function (error) {
                    modalService.openErrorModal('member FAILED to update');
                }
            );
        }
    });
