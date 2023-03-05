package com.deadshot.scorecard.utilities;

import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.models.CricketTeammate;

public class BallerUtility {

    public static String getBallerEconomy(CricketTeammate playerInfo) {
        double economy = 0;
        economy = (playerInfo.getBallsBalled() > 0) ?
                (((double) (playerInfo.getRunsConceded() * CommonConstants.SIX))
                        / ((double) playerInfo.getBallsBalled())) :
                0;
        return CommonUtility.round(economy, CommonConstants.TWO) +
                CommonConstants.EMPTY_STRING;
    }

    public static String getBallerOvers(CricketTeammate playerInfo) {
        String overs = "";
        overs = (playerInfo.getBallsBalled() > 0) ?
                (playerInfo.getBallsBalled()/6) + "." + (playerInfo.getBallsBalled()%6) :
                (0 + CommonConstants.EMPTY_STRING);
        return overs;
    }
}
