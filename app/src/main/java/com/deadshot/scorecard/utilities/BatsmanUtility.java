package com.deadshot.scorecard.utilities;

import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.models.CricketTeammate;

public class BatsmanUtility {

    public static String getBatsmanStrikeRate(CricketTeammate playerInfo){
        double strikeRate = 0;
        strikeRate = (playerInfo.getBallsPlayed() > 0) ?
                (((double) (playerInfo.getRunsScored()
                        * CommonConstants.HUNDRED)) /
                        ((double) playerInfo.getBallsPlayed())) :
                0;
        return CommonUtility.round(strikeRate, CommonConstants.TWO) + CommonConstants.EMPTY_STRING;
    }

}
