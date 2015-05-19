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
                    $scope.members = [{nickname: nickname, vkontakte: vkontakte}].concat($scope.members);
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
        };

        $scope.deleteMember = function (nickname, index) {
            httpService.get("member/games-number", {nickname: nickname},
                function (number) {
                    if (number> 0) {
                        modalService.openErrorModal('Unable to delete ' + nickname + '. This member has already played ' +
                            number + ' games');
                    } else {
                        httpService.delete("member", {nickname: nickname},
                            function () {
                                $scope.members.splice(index, 1);
                                modalService.openInfoModal('member successfully deleted!');
                            },
                            function (error) {
                                modalService.openErrorModal('member FAILED to delete');
                            }
                        );
                    }
                },
                function (error) {
                    modalService.openErrorModal('member FAILED to delete');
                }
            );
        };
    });
