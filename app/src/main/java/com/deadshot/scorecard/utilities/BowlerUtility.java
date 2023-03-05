package com.deadshot.scorecard.utilities;

import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.models.CricketTeammate;

public class BowlerUtility {

    public static String getBowlerEconomy(CricketTeammate playerInfo) {
        double economy = 0;
        economy = (playerInfo.getBallsBowled() > 0) ?
                (((double) (playerInfo.getRunsConceded() * CommonConstants.SIX))
                        / ((double) playerInfo.getBallsBowled())) :
                0;
        return CommonUtility.round(economy, CommonConstants.TWO) +
                CommonConstants.EMPTY_STRING;
    }

    public static String getBowlerOvers(CricketTeammate playerInfo) {
        String overs = "";
        overs = (playerInfo.getBallsBowled() > 0) ?
                (playerInfo.getBallsBowled()/6) + "." + (playerInfo.getBallsBowled()%6) :
                (0 + CommonConstants.EMPTY_STRING);
        return overs;
    }
}
