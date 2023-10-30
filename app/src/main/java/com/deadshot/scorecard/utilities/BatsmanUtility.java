package com.deadshot.scorecard.utilities;

import android.util.Log;

import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.models.CricketTeammate;

public class BatsmanUtility {

    public static String getBatsmanStrikeRate(CricketTeammate playerInfo){
        double strikeRate = 0;
        Log.d(CommonConstants.DEBUG_LOG_TAG, playerInfo.toString());
        strikeRate = (playerInfo.getBallsPlayed() > 0) ?
                (((double) (playerInfo.getRunsScored()
                        * CommonConstants.HUNDRED)) /
                        ((double) playerInfo.getBallsPlayed())) :
                0;
        return CommonUtility.round(strikeRate, CommonConstants.ONE) + CommonConstants.EMPTY_STRING;
    }

}
