package com.deadshot.scorecard.utilities;

import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.models.CricketTeammate;
import com.deadshot.scorecard.models.adapter.PlayerScorecard;

import java.util.ArrayList;
import java.util.List;

public class CommonUtility {

    public static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public static List<PlayerScorecard> getScorecardDetailsForAdapter(List<CricketTeammate> cricketTeammates) {
        List<PlayerScorecard> playerScorecards = new ArrayList<>();
        for(CricketTeammate cricketTeammate : cricketTeammates){
            PlayerScorecard playerScorecard = new PlayerScorecard();
            playerScorecard.setPlayerName(cricketTeammate.getPlayerName());
            playerScorecard.setBatsmanBallsPlayed(cricketTeammate.getBallsPlayed() + CommonConstants.EMPTY_STRING);
            playerScorecard.setBatsmanRuns(cricketTeammate.getRunsScored() + CommonConstants.EMPTY_STRING);
            playerScorecard.setBatsmanFoursScored(cricketTeammate.getFoursScored() + CommonConstants.EMPTY_STRING);
            playerScorecard.setBatsmanSixesScored(cricketTeammate.getSixesScored() + CommonConstants.EMPTY_STRING);
            playerScorecard.setBatsmanStrikeRate(BatsmanUtility.getBatsmanStrikeRate(cricketTeammate));
            playerScorecard.setBallerOvers(BallerUtility.getBallerOvers(cricketTeammate));
            playerScorecard.setBallerRunsConceded(cricketTeammate.getRunsConceded() + CommonConstants.EMPTY_STRING);
            playerScorecard.setBallerMaidens(cricketTeammate.getMaidensConceded() + CommonConstants.EMPTY_STRING);
            playerScorecard.setBallerWicketsTaken(cricketTeammate.getWicketsTaken() + CommonConstants.EMPTY_STRING);
            playerScorecard.setBallerEconomy(BallerUtility.getBallerEconomy(cricketTeammate));
            playerScorecards.add(playerScorecard);
        }
        return playerScorecards;
    }
}
