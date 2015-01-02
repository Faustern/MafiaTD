$(document).ready(function () {
    var players = [];
    $.ajax({
        type: 'POST',
        url: "/getAllPlayers",
        cache: false,
        success: function (playersFromDB) {
            $.each(playersFromDB, function (index, nickname) {
                players.push(nickname)
            });
        },
        error: function (xhr, textStatus, errorThrown) {
        }
    });

    var $gameTable = $('<table></table>').appendTo('body');
    var $trGameHeader = $('<tr></tr>').appendTo($gameTable);
    var $thSeason = $('<th>Season</th>').appendTo($trGameHeader);
    var $thDate = $('<th>Date</th>').appendTo($trGameHeader);
    var $thResult = $('<th>Result</th>').appendTo($trGameHeader);
    var $thMaster = $('<th>Master</th>').appendTo($trGameHeader);

    var $trGame = $('<tr></tr>').appendTo($gameTable);

    var $tdSeason = $('<td></td>').appendTo($trGame);
    var $inputSeason = $('<input value="4">').appendTo($tdSeason);
    $inputSeason.attr({id: 'season'});

    var $tdDate = $('<td></td>').appendTo($trGame);
    var $inputDate = $('<input class="datepicker date">').appendTo($tdDate);
    $inputDate.attr({id: 'date'});

    var $tdResult = $('<td></td>').appendTo($trGame);
    var $selectResult = $('<select></select>').appendTo($tdResult);
    $selectResult.attr({id: 'result'});

    var $optionResultCityClearWin = $('<option>clear city win</option>').appendTo($selectResult);
    var $optionResultCityWin = $('<option>city win</option>').appendTo($selectResult);
    var $optionResultMafiaWin = $('<option>mafia win</option>').appendTo($selectResult);
    var $optionResultMafiaClearWin = $('<option>clear mafia win</option>').appendTo($selectResult);

    $optionResultCityClearWin.attr({value: '1'});
    $optionResultCityWin.attr({value: '2'});
    $optionResultMafiaWin.attr({value: '3'});
    $optionResultMafiaClearWin.attr({value: '4'});


    var $tdMaster = $('<td></td>').appendTo($trGame);
    var $inputMaster = $('<input>').appendTo($tdMaster);
    $inputMaster.attr({id: 'master'});
    $("#master").autocomplete({
        source: players
    });


    $('<br>').appendTo('body');

    var $playerTable = $('<table></table>').appendTo('body');

    var $trHeader = $('<tr></tr>').appendTo($playerTable);
    var $thNumber = $('<th>Number</th>').appendTo($trHeader);
    var $thNickName = $('<th>NickName</th>').appendTo($trHeader);
    var $thRole = $('<th>Role</th>').appendTo($trHeader);
    var $thLife = $('<th>Life</th>').appendTo($trHeader);
    var $thBestVoices = $('<th>Best Voices</th>').appendTo($trHeader);
    var $thFinalDecision = $('<th>Final Decision</th>').appendTo($trHeader);
    var $thFouls = $('<th>Fouls</th>').appendTo($trHeader);
    var $thRating = $('<th>Rating</th>').appendTo($trHeader);
    var $thWho = $('<th>Кто выставил</th>').appendTo($trHeader);
    var $thWhom = $('<th>Кого выставил</th>').appendTo($trHeader);
    var $thHowMuch = $('<th>Сколько проголосовало</th>').appendTo($trHeader);

    for (var i = 1; i < 11; i++) {
        var $tr = $('<tr></tr>').appendTo($playerTable);

        var $tdNumber = $('<td></td>').appendTo($tr);
        var $labelNumber = $('<label>' + i + '</label>').appendTo($tdNumber);

        var $tdNickName = $('<td></td>').appendTo($tr);
        var $inputNickName = $('<input>').appendTo($tdNickName);
        $inputNickName.attr({id: 'nickName_' + i});

        $("#nickName_" + i).autocomplete({
            source: players
        });


        var $tdRole = $('<td></td>').appendTo($tr);
        var $selectRole = $('<select></select>').appendTo($tdRole);
        $selectRole.attr({id: 'role_' + i});

        var $optionRoleCitizen = $('<option  default>Citizen</option>').appendTo($selectRole);
        var $optionRoleMafia = $('<option>Mafia</option>').appendTo($selectRole);
        var $optionRoleDon = $('<option>Don</option>').appendTo($selectRole);
        var $optionRoleSheriff = $('<option>Sheriff</option>').appendTo($selectRole);

        $optionRoleDon.attr({value: '1'});
        $optionRoleMafia.attr({value: '2'});
        $optionRoleSheriff.attr({value: '4'});
        $optionRoleCitizen.attr({value: '3'});


        var $tdLife = $('<td></td>').appendTo($tr);
        var $selectLife = $('<select></select>').appendTo($tdLife);
        $selectLife.attr({id: 'life_' + i});

        var $optionLifeNotAway = $('<option  default>not away</option>').appendTo($selectLife);
        var $optionLifeKilledOneNight = $('<option>killed 1 night</option>').appendTo($selectLife);
        var $optionLifeKilledTwoNight = $('<option>killed 2 night</option>').appendTo($selectLife);
        var $optionLifeKilledThreeNight = $('<option>killed 3 night</option>').appendTo($selectLife);
        var $optionLifeKilledFourNight = $('<option>killed 4 night</option>').appendTo($selectLife);
        var $optionLifeKilledFivePlusNight = $('<option>killed 5 plus night</option>').appendTo($selectLife);
        var $optionLifeAwayZeroDay = $('<option>away 0 day</option>').appendTo($selectLife);
        var $optionLifeAwayOneDay = $('<option>away 1 day</option>').appendTo($selectLife);
        var $optionLifeAwayTwoDay = $('<option>away 2 day</option>').appendTo($selectLife);
        var $optionLifeAwayThreeDay = $('<option>away 3 day</option>').appendTo($selectLife);
        var $optionLifeAwayFourDay = $('<option>away 4 day</option>').appendTo($selectLife);
        var $optionLifeAwayFivePlusDay = $('<option>away 5 plus day</option>').appendTo($selectLife);

        $optionLifeNotAway.attr({value: '12'});
        $optionLifeKilledOneNight.attr({value: '1'});
        $optionLifeKilledTwoNight.attr({value: '2'});
        $optionLifeKilledThreeNight.attr({value: '3'});
        $optionLifeKilledFourNight.attr({value: '4'});
        $optionLifeKilledFivePlusNight.attr({value: '5'});
        $optionLifeAwayZeroDay.attr({value: '6'});
        $optionLifeAwayOneDay.attr({value: '7'});
        $optionLifeAwayTwoDay.attr({value: '8'});
        $optionLifeAwayThreeDay.attr({value: '9'});
        $optionLifeAwayFourDay.attr({value: '10'});
        $optionLifeAwayFivePlusDay.attr({value: '11'});


        var $tdBestVoices = $('<td></td>').appendTo($tr);
        var $selectBestVoices = $('<select></select>').appendTo($tdBestVoices);
        $selectBestVoices.attr({id: 'bestVoices_' + i});

        var $optionBestVoices0 = $('<option  default>0</option>').appendTo($selectBestVoices);
        var $optionBestVoices1 = $('<option>1</option>').appendTo($selectBestVoices);
        var $optionBestVoices2 = $('<option>2</option>').appendTo($selectBestVoices);
        var $optionBestVoices3 = $('<option>3</option>').appendTo($selectBestVoices);
        var $optionBestVoices4 = $('<option>4</option>').appendTo($selectBestVoices);
        var $optionBestVoices5 = $('<option>5</option>').appendTo($selectBestVoices);
        var $optionBestVoices6 = $('<option>6</option>').appendTo($selectBestVoices);
        var $optionBestVoices7 = $('<option>7</option>').appendTo($selectBestVoices);
        var $optionBestVoices8 = $('<option>8</option>').appendTo($selectBestVoices);
        var $optionBestVoices9 = $('<option>9</option>').appendTo($selectBestVoices);
        var $optionBestVoices10 = $('<option>10</option>').appendTo($selectBestVoices);

        $optionBestVoices0.attr({value: '0'});
        $optionBestVoices1.attr({value: '1'});
        $optionBestVoices2.attr({value: '2'});
        $optionBestVoices3.attr({value: '3'});
        $optionBestVoices4.attr({value: '4'});
        $optionBestVoices5.attr({value: '5'});
        $optionBestVoices6.attr({value: '6'});
        $optionBestVoices7.attr({value: '7'});
        $optionBestVoices8.attr({value: '8'});
        $optionBestVoices9.attr({value: '9'});
        $optionBestVoices10.attr({value: '10'});


        var $tdFinalDecision = $('<td></td>').appendTo($tr);
        var $selectFinalDecision = $('<select></select>').appendTo($tdFinalDecision);
        $selectFinalDecision.attr({id: 'finalDecision_' + i});

        var $optionFinalDecision0 = $('<option  default>0</option>').appendTo($selectFinalDecision);
        var $optionFinalDecision1 = $('<option>1</option>').appendTo($selectFinalDecision);
        var $optionFinalDecision2 = $('<option>2</option>').appendTo($selectFinalDecision);
        var $optionFinalDecision3 = $('<option>3</option>').appendTo($selectFinalDecision);
        var $optionFinalDecision4 = $('<option>4</option>').appendTo($selectFinalDecision);
        var $optionFinalDecision5 = $('<option>5</option>').appendTo($selectFinalDecision);
        var $optionFinalDecision6 = $('<option>6</option>').appendTo($selectFinalDecision);
        var $optionFinalDecision7 = $('<option>7</option>').appendTo($selectFinalDecision);
        var $optionFinalDecision8 = $('<option>8</option>').appendTo($selectFinalDecision);
        var $optionFinalDecision9 = $('<option>9</option>').appendTo($selectFinalDecision);
        var $optionFinalDecision10 = $('<option>10</option>').appendTo($selectFinalDecision);

        $optionFinalDecision0.attr({value: '0'});
        $optionFinalDecision1.attr({value: '1'});
        $optionFinalDecision2.attr({value: '2'});
        $optionFinalDecision3.attr({value: '3'});
        $optionFinalDecision4.attr({value: '4'});
        $optionFinalDecision5.attr({value: '5'});
        $optionFinalDecision6.attr({value: '6'});
        $optionFinalDecision7.attr({value: '7'});
        $optionFinalDecision8.attr({value: '8'});
        $optionFinalDecision9.attr({value: '9'});
        $optionFinalDecision10.attr({value: '10'});


        var $tdFouls = $('<td></td>').appendTo($tr);
        var $selectFouls = $('<select></select>').appendTo($tdFouls);
        $selectFouls.attr({id: 'fouls_' + i});

        var $optionFouls0 = $('<option  default>0</option>').appendTo($selectFouls);
        var $optionFouls1 = $('<option  default>1</option>').appendTo($selectFouls);
        var $optionFouls2 = $('<option  default>2</option>').appendTo($selectFouls);
        var $optionFouls3 = $('<option  default>3</option>').appendTo($selectFouls);
        var $optionFouls4 = $('<option  default>4</option>').appendTo($selectFouls);

        $optionFouls0.attr({value: '0'});
        $optionFouls1.attr({value: '1'});
        $optionFouls2.attr({value: '2'});
        $optionFouls3.attr({value: '3'});
        $optionFouls4.attr({value: '4'});


        var $tdRating = $('<td></td>').appendTo($tr);
        var $labelRating = $('<label</label>').appendTo($tdRating);
        $labelRating.attr({id: 'rating_' + i});

        var $tdWho = $('<td></td>').appendTo($tr);
        var $inputWho = $('<input type="text" class="voting"/>').appendTo($tdWho);

        var $tdWhom = $('<td></td>').appendTo($tr);
        var $inputWhom = $('<input type="text" class="voting"/>').appendTo($tdWhom);

        var $tdHowMany = $('<td></td>').appendTo($tr);
        var $inputHowMany = $('<input type="text" class="voting"/>').appendTo($tdHowMany);

    }
    $('<br>').appendTo('body');

    var $buttonCalculateRating = $('<button>Calculate Rating</button>').appendTo('body');
    $buttonCalculateRating.attr({id: 'calculateRating'});

    var $buttonSaveToDB = $('<button>Save To DB</button>').appendTo('body');
    $buttonSaveToDB.attr({id: 'saveToDB'});



    $('<br>').appendTo('body');
    var $selectShowRatingSeason = $('<select></select>').appendTo('body');
    $selectShowRatingSeason.attr({id: 'showRatingSeason'});
    var $optionShowRatingSeasonWinter2 = $('<option>Winter 14/15</option>').appendTo($selectShowRatingSeason);
    var $optionShowRatingSeasonAll = $('<option>All seasons</option>').appendTo($selectShowRatingSeason);
    var $optionShowRatingSeasonWinter = $('<option>Winter 13/14</option>').appendTo($selectShowRatingSeason);
    var $optionShowRatingSeasonSpring = $('<option>Spring 14</option>').appendTo($selectShowRatingSeason);
    $optionShowRatingSeasonWinter2.attr({value: '4'});
    $optionShowRatingSeasonAll.attr({value: '0'});
    $optionShowRatingSeasonWinter.attr({value: '1'});
    $optionShowRatingSeasonSpring.attr({value: '2'});
    var $buttonShowRating = $('<button>Show/Hide Rating</button>').appendTo('body');
    $buttonShowRating.attr({id: 'showHideRating'});

    $('<br>').appendTo('body')

    var $inputAddPlayerNickName = $('<input>').appendTo('body');
    $inputAddPlayerNickName.attr({id: 'nicknameAddPlayer'});
    var $inputAddPlayerVkontakte = $('<input hidden="hidden">').appendTo('body');
    $inputAddPlayerVkontakte.attr({id: 'vkontakteAddPlayer'});

    var $buttonAddPlayerToDB = $('<button>Add Player</button>').appendTo('body');
    $buttonAddPlayerToDB.attr({id: 'addPlayerToDB'});
    $('<br>').appendTo('body');
    var $buttonClearVoting = $('<button>Очистить после голосования</button>').appendTo('body');
    $buttonClearVoting.attr({id: 'clearVoting'});
    $('<br>').appendTo('body');


 /*   var $playerTableStatistics = $('<table></table>').appendTo('body');

    var $trHeaderStatistics = $('<tr></tr>').appendTo($playerTableStatistics);
    var $thNumberStatistics = $('<th>Number</th>').appendTo($trHeaderStatistics);
    var $thRoleStatistics = $('<th>Role</th>').appendTo($trHeaderStatistics);
    var $thLifeStatistics = $('<th>Life</th>').appendTo($trHeaderStatistics);
    var $thBestVoicesStatistics = $('<th>Best Voices</th>').appendTo($trHeaderStatistics);
    var $thFinalDecisionStatistics = $('<th>Final Decision</th>').appendTo($trHeaderStatistics);
    var $thFoulsStatistics = $('<th>Fouls</th>').appendTo($trHeaderStatistics);

    var $trStatistics = $('<tr></tr>').appendTo($playerTableStatistics);


    var $tdNumberStatistics = $('<td></td>').appendTo($trStatistics);
    $tdNumberStatistics.addClass('statistics');
    var numberStatistics = "";
    for(var i=1; i<11; i++) {
        numberStatistics = numberStatistics + '<input type="checkbox", id="numbers", ' +
            'value="' + i + '" checked>' + i + '<br>'
    }
    var $numberStatistics =  $(numberStatistics).appendTo($tdNumberStatistics);



    var $tdRoleStatistics = $('<td></td>').appendTo($trStatistics);
    $tdRoleStatistics.addClass('statistics');
    var $roleStatisticsDon =  $('<input checked>Don<br>').appendTo($tdRoleStatistics);
    var $roleStatisticsMafia =  $('<input checked>Mafia<br>').appendTo($tdRoleStatistics);
    var $roleStatisticsCitizen =  $('<input checked>Citizen<br>').appendTo($tdRoleStatistics);
    var $roleStatisticsSheriff =  $('<input checked>Sheriff<br>').appendTo($tdRoleStatistics);
    $roleStatisticsDon.attr({type: "checkbox", id: 'roles', value: '1'});
    $roleStatisticsMafia.attr({type: "checkbox", id: 'roles', value: '2'});
    $roleStatisticsCitizen.attr({type: "checkbox", id: 'roles', value: '3'});
    $roleStatisticsSheriff.attr({type: "checkbox", id: 'roles', value: '4'});


    var $tdLifeStatistics = $('<td></td>').appendTo($trStatistics);
    $tdLifeStatistics.addClass('statistics');
    var $lifeStatisticsNotAway =  $('<input checked>not away<br>').appendTo($tdLifeStatistics);
    var $lifeStatisticsKilledOneNight =  $('<input checked>killed 1 night<br>').appendTo($tdLifeStatistics);
    var $lifeStatisticsKilledTwoNight =  $('<input checked>killed 2 night<br>').appendTo($tdLifeStatistics);
    var $lifeStatisticsKilledThreeNight =  $('<input checked>killed 3 night<br>').appendTo($tdLifeStatistics);
    var $lifeStatisticsKilledFourNight =  $('<input checked>killed 4 night<br>').appendTo($tdLifeStatistics);
    var $lifeStatisticsKilledFivePlusNight =  $('<input checked>killed 5 plus night<br>').appendTo($tdLifeStatistics);
    var $lifeStatisticsAwayZeroDay =  $('<input checked>away 0 day<br>').appendTo($tdLifeStatistics);
    var $lifeStatisticsAwayOneDay =  $('<input checked>away 1 day<br>').appendTo($tdLifeStatistics);
    var $lifeStatisticsAwayTwoDay =  $('<input checked>away 2 day<br>').appendTo($tdLifeStatistics);
    var $lifeStatisticsAwayThreeDay =  $('<input checked>away 3 day<br>').appendTo($tdLifeStatistics);
    var $lifeStatisticsAwayFourDay =  $('<input checked>away 4 day<br>').appendTo($tdLifeStatistics);
    var $lifeStatisticsAwayFivePlusDay =  $('<input checked>away 5 plus day<br>').appendTo($tdLifeStatistics);

    $lifeStatisticsNotAway.attr({type: "checkbox", id: 'lives', value: '12'});
    $lifeStatisticsKilledOneNight.attr({type: "checkbox", id: 'lives', value: '1'});
    $lifeStatisticsKilledTwoNight.attr({type: "checkbox", id: 'lives', value: '2'});
    $lifeStatisticsKilledThreeNight.attr({type: "checkbox", id: 'lives', value: '3'});
    $lifeStatisticsKilledFourNight.attr({type: "checkbox", id: 'lives', value: '4'});
    $lifeStatisticsKilledFivePlusNight.attr({type: "checkbox", id: 'lives', value: '5'});
    $lifeStatisticsAwayZeroDay.attr({type: "checkbox", id: 'lives', value: '6'});
    $lifeStatisticsAwayOneDay.attr({type: "checkbox", id: 'lives', value: '7'});
    $lifeStatisticsAwayTwoDay.attr({type: "checkbox", id: 'lives', value: '8'});
    $lifeStatisticsAwayThreeDay.attr({type: "checkbox", id: 'lives', value: '9'});
    $lifeStatisticsAwayFourDay.attr({type: "checkbox", id: 'lives', value: '10'});
    $lifeStatisticsAwayFivePlusDay.attr({type: "checkbox", id: 'lives', value: '11'});


    var $tdBestVoicesStatistics = $('<td></td>').appendTo($trStatistics);
    $tdBestVoicesStatistics.addClass('statistics');
    var bestVoicesStatistics = "";
    for(var i=0; i<11; i++) {
        bestVoicesStatistics = bestVoicesStatistics + '<input type="checkbox", id="bestVoices", ' +
            'value="' + i + '" checked>' + i + '<br>'
    }
    var $bestVoicesStatistics =  $(bestVoicesStatistics).appendTo($tdBestVoicesStatistics);

    var $tdFinalDecisionStatistics = $('<td></td>').appendTo($trStatistics);
    $tdFinalDecisionStatistics.addClass('statistics');
    var finalDecisionStatistics = "";
    for(var i=0; i<2; i++) {
        finalDecisionStatistics = finalDecisionStatistics + '<input type="checkbox", id="finalDecisions", ' +
            'value="' + i + '" checked>' + i + '<br>'
    }
    for(var i=2; i<11; i++) {
        finalDecisionStatistics = finalDecisionStatistics + '<input type="checkbox", id="finalDecisions", ' +
            'value="' + i + '" checked>1/' + i + '<br>'
    }
    var $finalDecisionStatistics =  $(finalDecisionStatistics).appendTo($tdFinalDecisionStatistics);


    var $tdFoulsStatistics = $('<td></td>').appendTo($trStatistics);
    $tdFoulsStatistics.addClass('statistics');
    var foulsStatistics = "";
    for(var i=0; i<5; i++) {
        foulsStatistics = foulsStatistics + '<input type="checkbox", id="fouls", ' +
            'value="' + i + '" checked>' + i + '<br>'
    }
    var $foulsStatistics =  $(foulsStatistics).appendTo($tdFoulsStatistics);

    $('<br>').appendTo('body');
    var $selectStatisticsSeasonTable = $('<select></select>').appendTo('body');
    $selectStatisticsSeasonTable.attr({id: 'statisticsSeasonTable'});
    var $optionStatisticsSeasonTableAll = $('<option  default>All seasons</option>').appendTo($selectStatisticsSeasonTable);
    var $optionStatisticsSeasonTableWinter = $('<option>Winter</option>').appendTo($selectStatisticsSeasonTable);
    var $optionStatisticsSeasonTableSpring = $('<option>Spring</option>').appendTo($selectStatisticsSeasonTable);
    $optionStatisticsSeasonTableAll.attr({value: '0'});
    $optionStatisticsSeasonTableWinter.attr({value: '1'});
    $optionStatisticsSeasonTableSpring.attr({value: '2'});

    var $selectStatisticsCriteria = $('<select></select>').appendTo('body');
    $selectStatisticsCriteria.attr({id: 'statisticsCriteria'});
    var $optionStatisticsCriteriaWinProcent = $('<option  default>Win Procent</option>').appendTo($selectStatisticsCriteria);
    var $optionStatisticsCriteriaGames = $('<option>Games</option>').appendTo($selectStatisticsCriteria);
    var $optionStatisticsCriteriaGamesWin = $('<option>Games Win</option>').appendTo($selectStatisticsCriteria);
    $optionStatisticsCriteriaWinProcent.attr({value: '3'});
    $optionStatisticsCriteriaGames.attr({value: '1'});
    $optionStatisticsCriteriaGamesWin.attr({value: '2'});

    var $inputStatisticsLimit = $('<input>').appendTo('body');
    $inputStatisticsLimit.attr({id: 'statisticsLimit'});

    var $buttonStatistics = $('<button>Statistics</button>').appendTo('body');
    $buttonStatistics.attr({id: 'statisticsButton'});

    $('<br>').appendTo('body');

    //*************************************************************************************************************

    var $selectStatisticsPerGameSeason = $('<select></select>').appendTo('body');
    $selectStatisticsPerGameSeason.attr({id: 'statisticsPerGameSeason'});
    var $optionStatisticsPerGameSeasonAll = $('<option  default>All seasons</option>').appendTo($selectStatisticsPerGameSeason);
    var $optionStatisticsPerGameSeasonWinter = $('<option>Winter</option>').appendTo($selectStatisticsPerGameSeason);
    var $optionStatisticsPerGameSeasonSpring = $('<option>Spring</option>').appendTo($selectStatisticsPerGameSeason);
    $optionStatisticsPerGameSeasonAll.attr({value: '0'});
    $optionStatisticsPerGameSeasonWinter.attr({value: '1'});
    $optionStatisticsPerGameSeasonSpring.attr({value: '2'});

    var $selectStatisticsPerGameCriteria = $('<select></select>').appendTo('body');
    $selectStatisticsPerGameCriteria.attr({id: 'statisticsPerGameCriteria'});
    var $optionStatisticsPerGameCriteriaBestVoices = $('<option  default>Best Voices</option>').appendTo($selectStatisticsPerGameCriteria);
    var $optionStatisticsPerGameCriteriaFouls = $('<option>Fouls</option>').appendTo($selectStatisticsPerGameCriteria);
    $optionStatisticsPerGameCriteriaBestVoices.attr({value: '11'});
    $optionStatisticsPerGameCriteriaFouls.attr({value: '12'});

    var $inputStatisticsPerGameLimit = $('<input>').appendTo('body');
    $inputStatisticsPerGameLimit.attr({id: 'statisticsPerGameLimit'});

    var $buttonStatisticsPerGame = $('<button>Statistics Per Game</button>').appendTo('body');
    $buttonStatisticsPerGame.attr({id: 'statisticsPerGameButton'});

    $('<br>').appendTo('body');


    //***********************************************PLOT***********************************************************

    var $selectPlotSeason = $('<select></select>').appendTo('body');
    $selectPlotSeason.attr({id: 'plotSeason'});
    var $optionPlotSeasonAll = $('<option  default>All seasons</option>').appendTo($selectPlotSeason);
    var $optionPlotSeasonWinter = $('<option>Winter</option>').appendTo($selectPlotSeason);
    var $optionPlotSeasonSpring = $('<option>Spring</option>').appendTo($selectPlotSeason);
    $optionPlotSeasonAll.attr({value: '0'});
    $optionPlotSeasonWinter.attr({value: '1'});
    $optionPlotSeasonSpring.attr({value: '2'});

    var $inputPlotNickName = $('<input>').appendTo('body');
    $inputPlotNickName.attr({id: 'plotNickName'});
    players.push('all');
    $("#plotNickName").autocomplete({
        source: players
    });


    var $selectPlotRole = $('<select></select>').appendTo('body');
    $selectPlotRole.attr({id: 'plotRole'});
    var $optionPlotRoleAll = $('<option  default>all</option>').appendTo($selectPlotRole);
    var $optionPlotRoleCitizen = $('<option>Citizen</option>').appendTo($selectPlotRole);
    var $optionPlotRoleMafia = $('<option>Mafia</option>').appendTo($selectPlotRole);
    var $optionPlotRoleDon = $('<option>Don</option>').appendTo($selectPlotRole);
    var $optionPlotRoleSheriff = $('<option>Sheriff</option>').appendTo($selectPlotRole);
    $optionPlotRoleAll.attr({value: '0'});
    $optionPlotRoleDon.attr({value: '1'});
    $optionPlotRoleMafia.attr({value: '2'});
    $optionPlotRoleSheriff.attr({value: '4'});
    $optionPlotRoleCitizen.attr({value: '3'});


    var $selectPlotCriteria = $('<select></select>').appendTo('body');
    $selectPlotCriteria.attr({id: 'plotCriteria'});
    var $optionPlotCriteriaWinProcent = $('<option  default>Win Procent</option>').appendTo($selectPlotCriteria);
    var $optionPlotCriteriaGames = $('<option>Games</option>').appendTo($selectPlotCriteria);
    var $optionPlotCriteriaGamesWin = $('<option>Games Win</option>').appendTo($selectPlotCriteria);
    $optionPlotCriteriaWinProcent.attr({value: '3'});
    $optionPlotCriteriaGames.attr({value: '1'});
    $optionPlotCriteriaGamesWin.attr({value: '2'});

    var $buttonDrawPlots = $('<button>Get Plot</button>').appendTo('body');
    $buttonDrawPlots.attr({id: 'drawPlotsButton'});

//*****************************************************************************************************
    $('<br>').appendTo('body');
    var $selectStatisticsSeason = $('<select></select>').appendTo('body');
    $selectStatisticsSeason.attr({id: 'statisticsSeason'});
    var $optionStatisticsSeasonAll = $('<option  default>All seasons</option>').appendTo($selectStatisticsSeason);
    var $optionStatisticsSeasonWinter = $('<option>Winter</option>').appendTo($selectStatisticsSeason);
    var $optionStatisticsSeasonSpring = $('<option>Spring</option>').appendTo($selectStatisticsSeason);
    $optionStatisticsSeasonAll.attr({value: '0'});
    $optionStatisticsSeasonWinter.attr({value: '1'});
    $optionStatisticsSeasonSpring.attr({value: '2'});
    var $inputStatisticsNickName = $('<input>').appendTo('body');
    $inputStatisticsNickName.attr({id: 'statisticsNickname'});
    $("#statisticsNickname").autocomplete({
        source: players
    });
    var $buttonStatisticsPlayer = $('<button>Statistics Player</button>').appendTo('body');
    $buttonStatisticsPlayer.attr({id: 'statisticsPlayerButton'});
    $('<br>').appendTo('body');
    var $playerStatisticsTable = $('<table></table>').appendTo('body');
//*****************************************************************************************************


    $('<br>').appendTo('body');
    var $playerTableRoles = $('<table></table>').appendTo('body');

    var $trHeaderRoles = $('<tr></tr>').appendTo($playerTableRoles);
    var $thRoles1 = $('<th>1</th>').appendTo($trHeaderRoles);
    var $thRoles2 = $('<th>2</th>').appendTo($trHeaderRoles);
    var $thRoles3 = $('<th>3</th>').appendTo($trHeaderRoles);
    var $thRoles4 = $('<th>4</th>').appendTo($trHeaderRoles);
    var $thRoles5 = $('<th>5</th>').appendTo($trHeaderRoles);
    var $thRoles6 = $('<th>6</th>').appendTo($trHeaderRoles);
    var $thRoles7 = $('<th>7</th>').appendTo($trHeaderRoles);
    var $thRoles8 = $('<th>8</th>').appendTo($trHeaderRoles);
    var $thRoles9 = $('<th>9</th>').appendTo($trHeaderRoles);
    var $thRoles10 = $('<th>10</th>').appendTo($trHeaderRoles);

    var $trRoles = $('<tr></tr>').appendTo($playerTableRoles);

    var $tdRoles1 = $('<td></td>').appendTo($trRoles);
    var $tdRoles2 = $('<td></td>').appendTo($trRoles);
    var $tdRoles3 = $('<td></td>').appendTo($trRoles);
    var $tdRoles4 = $('<td></td>').appendTo($trRoles);
    var $tdRoles5 = $('<td></td>').appendTo($trRoles);
    var $tdRoles6 = $('<td></td>').appendTo($trRoles);
    var $tdRoles7 = $('<td></td>').appendTo($trRoles);
    var $tdRoles8 = $('<td></td>').appendTo($trRoles);
    var $tdRoles9 = $('<td></td>').appendTo($trRoles);
    var $tdRoles10 = $('<td></td>').appendTo($trRoles);


    for (var i=1; i<11; i++) {
        var $rolesDon =  $('<input checked>Don<br>').appendTo($trRoles.find('td').eq(i-1));
        var $rolesMafia =  $('<input checked>Mafia<br>').appendTo($trRoles.find('td').eq(i-1));
        var $rolesCitizen =  $('<input checked>Citizen<br>').appendTo($trRoles.find('td').eq(i-1));
        var $rolesSheriff =  $('<input checked>Sheriff<br>').appendTo($trRoles.find('td').eq(i-1));
        $rolesDon.attr({type: "checkbox", id: 'rolesDistribution'+i, value: '1'});
        $rolesMafia.attr({type: "checkbox", id: 'rolesDistribution'+i, value: '2'});
        $rolesCitizen.attr({type: "checkbox", id: 'rolesDistribution'+i, value: '3'});
        $rolesSheriff.attr({type: "checkbox", id: 'rolesDistribution'+i, value: '4'});
    }

    $('<br>').appendTo('body');
    var $selectRolesDistributionSeason = $('<select></select>').appendTo('body');
    $selectRolesDistributionSeason.attr({id: 'rolesDistributionSeason'});
    var $optionRolesDistributionSeasonAll = $('<option  default>All seasons</option>').appendTo($selectRolesDistributionSeason);
    var $optionRolesDistributionSeasonWinter = $('<option>Winter</option>').appendTo($selectRolesDistributionSeason);
    var $optionRolesDistributionSeasonSpring = $('<option>Spring</option>').appendTo($selectRolesDistributionSeason);
    $optionRolesDistributionSeasonAll.attr({value: '0'});
    $optionRolesDistributionSeasonWinter.attr({value: '1'});
    $optionRolesDistributionSeasonSpring.attr({value: '2'});
    var $buttonRolesDistribution = $('<button>Role Distribution Result</button>').appendTo('body');
    $buttonRolesDistribution.attr({id: 'rolesDistributionButton'});
    $('<br>').appendTo('body');
    var $roleDistributionTable = $('<table></table>').appendTo('body');

//*****************************************************************************************************
    $('<br>').appendTo('body');
    var $statisticsResultTable = $('<table></table>').appendTo('body');

    $('<br>').appendTo('body');
    var $statisticsPerGameResultTable = $('<table></table>').appendTo('body');

    var $trGame = $('<tr></tr>').appendTo($gameTable);

    var $chart1 = $(' <div id="chart1" style="margin-top:20px; margin-left:20px; width:600px; height:300px;"></div>');
    $chart1.appendTo('body');

    */

    var DATE_FORMAT = "dd-mm-yy";
    $( ".datepicker" ).datepicker({
        showOn: "button",
        buttonImage: "/resources/images/cal.png",
        buttonImageOnly: true,
        constrainInput: false,
        dateFormat: DATE_FORMAT
    });
    $('#date').val($.datepicker.formatDate(DATE_FORMAT, new Date()));


    var getSeason = function () {
        console.log($('#season').val());
        return $('#season').val();
    }

    var getDate = function () {
        console.log($('#date').val());
        return $('#date').val();
    }

    var getResult = function () {
        console.log($('#result').val());
        return $('#result').val();
    }

    var getMaster = function () {
        console.log($('#master').val());
        return $('#master').val();
    }

    var getNicknames = function () {
        var nicknames = [];
        for (var i = 1; i < 11; i++) {
            nicknames.push($('#nickName_' + i).val())
        }
        console.log(nicknames);
        return nicknames;
    }

    var getRoles = function () {
        var roles = [];
        for (var i = 1; i < 11; i++) {
            roles.push($('#role_' + i).val())
        }
        console.log(roles);
        return roles;
    }

    var getLives = function () {
        var lives = [];
        for (var i = 1; i < 11; i++) {
            lives.push($('#life_' + i).val())
        }
        console.log(lives);
        return lives;
    }

    var getBestVoices = function () {
        var bestVoices = [];
        for (var i = 1; i < 11; i++) {
            bestVoices.push($('#bestVoices_' + i).val())
        }
        console.log(bestVoices);
        return bestVoices;
    }

    var getFinalDecision = function () {
        var finalDecisions = [];
        for (var i = 1; i < 11; i++) {
            finalDecisions.push($('#finalDecision_' + i).val())
        }
        console.log(finalDecisions);
        return finalDecisions;
    }

    var getFouls = function () {
        var fouls = [];
        for (var i = 1; i < 11; i++) {
            fouls.push($('#fouls_' + i).val())
        }
        console.log(fouls);
        return fouls;
    }

    var currentGamePlayers = [];

    $('#calculateRating').unbind();
    $('#calculateRating').click(function () {
        $.ajax({
            type: 'POST',
            url: "/calculateRating",
            cache: false,
            data: {
                season: getSeason(),
                date: getDate(),
                result: getResult(),
                master: getMaster(),
                nickNames: getNicknames(),
                roles: getRoles(),
                lives: getLives(),
                bestVoices: getBestVoices(),
                finalDecisions: getFinalDecision(),
                fouls: getFouls()
            },
            success: function (statistics) {
                currentGamePlayers = statistics;
                $.each(statistics, function (index, statistic) {
                    $('#rating_' + (index + 1)).text(statistic.totalRating);
                });
            },
            error: function (xhr, textStatus, errorThrown) {
            }
        });
    });

    var showRating = function(results) {
        $("#ratingStrings").remove();
        var $ratingStrings = '<div id="ratingStrings">';
        $.each(results, function (index, result) {
            if (result.gamesPlayed.length>0) {

                var prefix = '';
                if (10<result.gamesPlayed.length) {
                    prefix = index + 1 + '. ';
                }

                $ratingStrings = $ratingStrings + prefix + '@' + result.player.vkontakte  +
                    ' ('+ result.player.nickname + ')  ' +
                    result.rating.toString().match('^[0-9]+[.]*[0-9]{0,2}')[0] +
                    '% (' + result.gamesPlayed.length +')' + '<br>';
            }
        });
        $ratingStrings = $ratingStrings + '</div>';
        $($ratingStrings).appendTo('body')
    }

    $('#saveToDB').unbind();
    $('#saveToDB').click(function () {
        $.ajax({
            type: 'POST',
            url: "/saveGameIntoDB",
            data: {
                season: getSeason(),
                date: getDate(),
                result: getResult(),
                master: getMaster(),
                nickNames: getNicknames(),
                roles: getRoles(),
                lives: getLives(),
                bestVoices: getBestVoices(),
                finalDecisions: getFinalDecision(),
                fouls: getFouls()
            },
            cache: false,
            success: function (results) {
                alert('game successfully saved!');
                showRating(results);
            },
            error: function (xhr, textStatus, errorThrown) {
                alert('game FAILED to save');
            }
        });


    });


    $('#addPlayerToDB').unbind();
    $('#addPlayerToDB').click(function () {
        $.ajax({
            type: 'POST',
            url: "/addPlayerToDB",
            cache: false,
            data: {
                nickname: $('#nicknameAddPlayer').val(),
                vkontakte: $('#vkontakteAddPlayer').val()
            },
            success: function (playersFromDB) {
                var playersAfterAdd = [];
                $.each(playersFromDB, function (index, nickname) {
                    playersAfterAdd.push(nickname)
                });
                for (var i = 1; i < 11; i++) {
                    $("#nickName_" + i).autocomplete({
                        source: playersAfterAdd
                    });
                }
                playersAfterAdd.push('all');
                $("#plotNickName").autocomplete({
                    source: playersAfterAdd
                });
                alert('Player successfully added!');
            },
            error: function (xhr, textStatus, errorThrown) {
                alert('player FAILED to add');
            }
        });
    });


    $('#showHideRating').unbind();
    $('#showHideRating').click(function () {

        if ($("#ratingStrings").length > 0) {
            $("#ratingStrings").remove();
        } else {
            $.ajax({
                type: 'POST',
                url: "/showPlayersRating",
                data: {
                    season: $('#showRatingSeason').val()
                },
                cache: false,
                success: function (results) {
                    showRating(results);
                },
                error: function (xhr, textStatus, errorThrown) {
                }
            });
        }
    });

    $('#statisticsButton').unbind();
    $('#statisticsButton').click(function () {

        var numbers = [];
        $.each($('#numbers:checked'), function (index, element) {
            numbers.push($(element).val());
        });

        var roles = [];
        $.each($('#roles:checked'), function (index, element) {
            roles.push($(element).val());
        });

        var lives = [];
        $.each($('#lives:checked'), function (index, element) {
            lives.push($(element).val());
        });

        var bestVoices = [];
        $.each($('#bestVoices:checked'), function (index, element) {
            bestVoices.push($(element).val());
        });

        var finalDecisions = [];
        $.each($('#finalDecisions:checked'), function (index, element) {
            finalDecisions.push($(element).val());
        });

        var fouls = [];
        $.each($('#fouls:checked'), function (index, element) {
            fouls.push($(element).val());
        });


        $.ajax({
            type: 'POST',
            url: "/statistics",
            cache: false,
            data: {
                numbers : numbers,
                roles : roles,
                lives : lives,
                bestVoices : bestVoices,
                finalDecisions : finalDecisions,
                fouls : fouls,
                criteria : $('#statisticsCriteria').val(),
                limit : $('#statisticsLimit').val(),
                season : $('#statisticsSeasonTable').val()
            },
            success: function (statisticsResults) {
                var trsStatisticsTable = '';
                $statisticsResultTable.empty();
                var $trStatisticsHeader = $('<tr></tr>').appendTo($statisticsResultTable);
                var $thPlaceStatistics = $('<th>#</th>').appendTo($trStatisticsHeader);
                var $thNicknameStatistics = $('<th>Ник</th>').appendTo($trStatisticsHeader);
                var $thGamesStatistics = $('<th>Игры</th>').appendTo($trStatisticsHeader);
                var $thGamesWinStatistics = $('<th>Выигранные игры</th>').appendTo($trStatisticsHeader);
                var $thWinProcentStatistics = $('<th>Процент побед</th>').appendTo($trStatisticsHeader)

                $.each(statisticsResults, function (index, statisticsResult) {
                    trsStatisticsTable = trsStatisticsTable  + '<tr><td>' + (index+1) +
                        '.</td><td>' + statisticsResult.player.nickname + '</td><td>' +
                        statisticsResult.games + '</td><td>' + statisticsResult.gamesWin +
                        '</td><td>' + statisticsResult.winProcent + '</td></tr>'
                });

                var $trsStatisticsTable =  $(trsStatisticsTable).appendTo($statisticsResultTable);

            },
            error: function (xhr, textStatus, errorThrown) {
            }
        });
    });


    $('#statisticsPerGameButton').unbind();
    $('#statisticsPerGameButton').click(function () {
        var limit = $('#statisticsPerGameLimit').val();
        var criteria = $('#statisticsPerGameCriteria').val();
        var season = $('#statisticsPerGameSeason').val();

        $.ajax({
            type: 'POST',
            url: "/per_game_statistics",
            cache: false,
            data: {
                limit : limit,
                criteria : criteria,
                season : season
            },
            success: function (perGameStatistics) {
                var trsStatisticsPerGameTable = '';
                $statisticsPerGameResultTable.empty();
                var $trStatisticsPerGameHeader = $('<tr></tr>').appendTo($statisticsPerGameResultTable);
                var $thPlacePerGameStatistics = $('<th>#</th>').appendTo($trStatisticsPerGameHeader);
                var $thNicknamePerGameStatistics = $('<th>Ник</th>').appendTo($trStatisticsPerGameHeader);
                var $thResultPerGameStatistics;

                if(criteria == 11)
                    $thResultPerGameStatistics = $('<th>Лучших в среднем за игру</th>').appendTo($trStatisticsPerGameHeader);
                if (criteria == 12)
                    $thResultPerGameStatistics = $('<th>Фолов в среднем за игру</th>').appendTo($trStatisticsPerGameHeader);



                $.each(perGameStatistics, function (index, statistics) {
                    trsStatisticsPerGameTable = trsStatisticsPerGameTable  + '<tr><td>' + (index+1) +
                        '.</td><td>' + statistics.nickname + '</td><td>' +  (criteria == 11 ?
                        statistics.bestVoicesPerGame.toString().match('^[0-9]+[.]*[0-9]{0,2}')[0] :
                        statistics.foulsPerGame.toString().match('^[0-9]+[.]*[0-9]{0,2}')[0]) + '</td></tr>'
                });

                var $trsStatisticsPerGameTable =  $(trsStatisticsPerGameTable).appendTo($statisticsPerGameResultTable);

            },
            error: function (xhr, textStatus, errorThrown) {
            }
        });
    });



    $('#statisticsPlayerButton').unbind();
    $('#statisticsPlayerButton').click(function () {
        $.ajax({
            type: 'POST',
            url: "/statisticsPlayer",
            cache: false,
            data: {
                nickname : $('#statisticsNickname').val(),
                season : $('#statisticsSeason').val()
            },
            success: function (playerStatistics) {
                $playerStatisticsTable.empty();

                var regExp = new RegExp('^[0-9]+[.]*[0-9]{0,2}');

                var $trNickname = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdNicknameHeader = $('<th>Ник</th>').appendTo($trNickname);
                var $tdNickname = $('<th>' + playerStatistics.nickname + '</th>').appendTo($trNickname);

                var $trGamesTotal = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdGamesTotalHeader = $('<td>Количество сыгранных игр</td>').appendTo($trGamesTotal);
                var $tdGamesTotal = $('<td>' + playerStatistics.gamesTotal + '</td>').appendTo($trGamesTotal);
                var $trWinTotal = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdWinTotalHeader = $('<td>Процент побед</td>').appendTo($trWinTotal);
                var $tdWinTotal = $('<td>' + playerStatistics.winTotal.toString().match(regExp)[0] +
                    '%</td>').appendTo($trWinTotal);

                var $trGamesDon = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdGamesDonHeader = $('<td>Количество сыгранных игр доном</td>').appendTo($trGamesDon);
                var $tdGamesDon = $('<td>' + playerStatistics.gamesDon + '</td>').appendTo($trGamesDon);
                var $trWinDon = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdWinDonHeader = $('<td>Процент побед доном</td>').appendTo($trWinDon);
                var $tdWinDon = $('<td>' + playerStatistics.winDon.toString().match(regExp)[0] +
                    '%</td>').appendTo($trWinDon);

                var $trGamesMafia = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdGamesMafiaHeader = $('<td>Количество сыгранных игр мафией</td>').appendTo($trGamesMafia);
                var $tdGamesMafia = $('<td>' + playerStatistics.gamesMafia + '</td>').appendTo($trGamesMafia);
                var $trWinMafia = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdWinMafiaHeader = $('<td>Процент побед мафией</td>').appendTo($trWinMafia);
                var $tdWinMafia = $('<td>' + playerStatistics.winMafia.toString().match(regExp)[0] +
                    '%</td>').appendTo($trWinMafia);

                var $trGamesCitizen = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdGamesCitizenHeader = $('<td>Количество сыгранных игр мирным</td>').appendTo($trGamesCitizen);
                var $tdGamesCitizen = $('<td>' + playerStatistics.gamesCitizen + '</td>').appendTo($trGamesCitizen);
                var $trWinCitizen = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdWinCitizenHeader = $('<td>Процент побед мирным</td>').appendTo($trWinCitizen);
                var $tdWinCitizen = $('<td>' + playerStatistics.winCitizen.toString().match(regExp)[0] +
                    '%</td>').appendTo($trWinCitizen);

                var $trGamesSheriff = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdGamesSheriffHeader = $('<td>Количество сыгранных игр шерифом</td>').appendTo($trGamesSheriff);
                var $tdGamesSheriff = $('<td>' + playerStatistics.gamesSheriff + '</td>').appendTo($trGamesSheriff);
                var $trWinSheriff = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdWinSheriffHeader = $('<td>Процент побед шерифом</td>').appendTo($trWinSheriff);
                var $tdWinSheriff = $('<td>' + playerStatistics.winSheriff.toString().match(regExp)[0] +
                    '%</td>').appendTo($trWinSheriff);

                var $trFoulsPerGame = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdFoulsPerGameHeader = $('<td>Количество фолов в среднем за игру</td>').appendTo($trFoulsPerGame);
                var $tdFoulsPerGame = $('<td>' + playerStatistics.foulsPerGame.toString().match(regExp)[0] +
                    '</td>').appendTo($trFoulsPerGame);

                var $trBestVoicesPerGame = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdBestVoicesPerGameHeader = $('<td>Количество голосов за лучшего в среднем за игру</td>').appendTo($trBestVoicesPerGame);
                var $tdBestVoicesPerGame = $('<td>' + playerStatistics.bestVoicesPerGame.toString().match(regExp)[0] +
                    '</td>').appendTo($trBestVoicesPerGame);

                var $trFinalDecisions = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdFinalDecisionsHeader = $('<td>Количество финальных решений</td>').appendTo($trFinalDecisions);
                var $tdFinalDecisions = $('<td>' + playerStatistics.finalDecisions + '</td>').appendTo($trFinalDecisions);

                var $trKilledOneNight = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdKilledOneNightHeader = $('<td>Убивался в первую ночь (кол. раз) </td>').appendTo($trKilledOneNight);
                var $tdKilledOneNight = $('<td>' + playerStatistics.killedOneNight + '</td>').appendTo($trKilledOneNight);

                var $trKilled = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdKilledHeader = $('<td>Убивался вообще (кол. раз) </td>').appendTo($trKilled);
                var $tdKilled = $('<td>' + playerStatistics.killed + '</td>').appendTo($trKilled);

                var $trRedAway = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdRedAwayHeader = $('<td>Уходил красным после голосованию (кол. раз) </td>').appendTo($trRedAway);
                var $tdRedAway = $('<td>' + playerStatistics.redAway + '</td>').appendTo($trRedAway);

                var $trRedAwayOneDay = $('<tr></tr>').appendTo($playerStatisticsTable);
                var $tdRedAwayOneDayHeader = $('<td>Сливался красным при девятерых (кол. раз) </td>').appendTo($trRedAwayOneDay);
                var $tdRedAwayOneDay = $('<td>' + playerStatistics.redAwayOneDay + '</td>').appendTo($trRedAwayOneDay);

            },
            error: function (xhr, textStatus, errorThrown) {
            }
        });
    });

    $('#drawPlotsButton').unbind();
    $('#drawPlotsButton').click(function () {
            $.ajax({
                type: 'POST',
                url: "/season_plot",
                data: {
                    nickname : $('#plotNickName').val(),
                    role : $('#plotRole').val(),
                    criteria : $('#plotCriteria').val(),
                    season : $('#plotSeason').val()
                },
                cache: false,
                success: function (statisticsResults) {
                    $('#chart1').empty();
                    var plotData = [];
                    var plotData2 = [];
                    var totalData = [];
                    var ylabel = "";

                    $.each(statisticsResults, function (numberIndex, numberResults) {
                        switch ($('#plotCriteria').val()) {
                            case "1":
                            case "2":
                                plotData.push(numberResults.games);
                                plotData2.push(numberResults.gamesWin);
                                totalData = [plotData,plotData2];
                                break;
                            case "3":
                                plotData.push(numberResults.winProcent);
                                totalData = [plotData]; break;
                                break;
                        }
                    });

                    switch ($('#plotCriteria').val()) {
                        case "1":
                        case "2": ylabel = ylabel + "К-во игр / К-во выиграных игр"; break;
                        case "3": ylabel = ylabel + "Процент выигрышей "; break;
                    }

                    switch ($('#plotRole').val()) {
                        case "0": ylabel = ylabel + "(Все роли)"; break;
                        case "1": ylabel = ylabel + "(Дон)";  break;
                        case "2": ylabel = ylabel + "(Мафия)"; break;
                        case "3": ylabel = ylabel + "(Мирный)"; break;
                        case "4": ylabel = ylabel + "(Шериф)"; break;
                    }

                            $.jqplot('chart1', totalData, {
                        series:[{showMarker:false}],
                        axes:{
                            xaxis:{
                                label:'Номер',
                                pad: 0,
                                labelRenderer: $.jqplot.CanvasAxisLabelRenderer
                            },
                            yaxis:{
                                label: ylabel,
                                labelRenderer: $.jqplot.CanvasAxisLabelRenderer
                            }
                        }
                    });
                },
                error: function (xhr, textStatus, errorThrown) {
                }
            });
    });


    $('#rolesDistributionButton').unbind();
    $('#rolesDistributionButton').click(function () {

        var roles = [];
        for (var i=1;i<11;i++) {
            roles[i-1] = [];
            $.each($('#rolesDistribution'+i+':checked'), function (index, element) {
                roles[i-1].push($(element).val());
            });
        }

        $.ajax({
            type: 'POST',
            url: "/role_distribution_result",
            cache: false,
            data: {
                season : $('#rolesDistributionSeason').val(),
                roles1 : roles[0],
                roles2 : roles[1],
                roles3 : roles[2],
                roles4 : roles[3],
                roles5 : roles[4],
                roles6 : roles[5],
                roles7 : roles[6],
                roles8 : roles[7],
                roles9 : roles[8],
                roles10 : roles[9]
            },
            success: function (resultPrediction) {
                console.log(resultPrediction);

                $roleDistributionTable.empty();

                var regExp = new RegExp('^[0-9]+[.]*[0-9]{0,2}');

                var $trSampling = $('<tr></tr>').appendTo($roleDistributionTable);
                var $tdSamplingHeader = $('<th>Выборка (к-во игр)</th>').appendTo($trSampling);
                var $tdSampling = $('<th>' + resultPrediction.sampling + '</th>').appendTo($trSampling);

                var $trOccurrenceProbability = $('<tr></tr>').appendTo($roleDistributionTable);
                var $tdOccurrenceProbabilityHeader = $('<td>Процент игр с таким распределением от общего кол-ва</td>').appendTo($trOccurrenceProbability);
                var $tdOccurrenceProbability = $('<td>' + resultPrediction.occurrenceProbability.toString().match(regExp)[0]
                    + '%</td>').appendTo($trOccurrenceProbability);

                var $trСityWinProbability = $('<tr></tr>').appendTo($roleDistributionTable);
                var $tdСityWinProbabilityHeader = $('<td>Процент побед города при таком распределении</td>').appendTo($trСityWinProbability);
                var $tdСityWinProbability = $('<td>' + resultPrediction.cityWinProbability.toString().match(regExp)[0]
                    + '%</td>').appendTo($trСityWinProbability);

                var $trСityClearWinProbability = $('<tr></tr>').appendTo($roleDistributionTable);
                var $tdСityClearWinProbabilityHeader = $('<td>Процент сухих побед города при таком распределении</td>').appendTo($trСityClearWinProbability);
                var $tdСityClearWinProbability = $('<td>' + resultPrediction.cityClearWinProbability.toString().match(regExp)[0]
                    + '%</td>').appendTo($trСityClearWinProbability);

                var $trMafiaWinProbability = $('<tr></tr>').appendTo($roleDistributionTable);
                var $tdMafiaWinProbabilityHeader = $('<td>Процент побед мафии при таком распределении</td>').appendTo($trMafiaWinProbability);
                var $tdMafiaWinProbability = $('<td>' + resultPrediction.mafiaWinProbability.toString().match(regExp)[0]
                    + '%</td>').appendTo($trMafiaWinProbability);

                var $trMafiaClearWinProbability = $('<tr></tr>').appendTo($roleDistributionTable);
                var $tdMafiaClearWinProbabilityHeader = $('<td>Процент сухих побед мафии при таком распределении</td>').appendTo($trMafiaClearWinProbability);
                var $tdMafiaClearWinProbability = $('<td>' + resultPrediction.mafiaClearWinProbability.toString().match(regExp)[0]
                    + '%</td>').appendTo($trMafiaClearWinProbability);

            },
            error: function (xhr, textStatus, errorThrown) {
            }
        });
    });

    $('#clearVoting').unbind();
    $('#clearVoting').click(function () {
          $(".voting").val("");
    });
});