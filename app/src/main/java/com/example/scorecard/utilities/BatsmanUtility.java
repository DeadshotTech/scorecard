package com.example.scorecard.utilities;

import com.example.scorecard.CommonConstants;
import com.example.scorecard.models.CricketTeammate;

public class BatsmanUtility {

    public static String getBatsmanStrikeRate(CricketTeammate playerInfo){
        double strikeRate = 0;
        strikeRate = (playerInfo.getBallsPlayed() > 0) ?
                (((double) playerInfo.getRunsScored()) / ((double) playerInfo.getBallsPlayed())) :
                0;
        return CommonUtility.round(strikeRate, CommonConstants.TWO) + CommonConstants.EMPTY_STRING;
    }

}
