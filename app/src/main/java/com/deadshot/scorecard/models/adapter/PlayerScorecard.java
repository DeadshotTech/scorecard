package com.deadshot.scorecard.models.adapter;

import java.util.Objects;

public class PlayerScorecard {

    private String playerName;
    private String batsmanRuns;
    private String batsmanBallsPlayed;
    private String batsmanFoursScored;
    private String batsmanSixesScored;
    private String batsmanStrikeRate;
    private String ballerOvers;
    private String ballerMaidens;
    private String ballerRunsConceded;
    private String ballerWicketsTaken;
    private String ballerEconomy;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getBatsmanRuns() {
        return batsmanRuns;
    }

    public void setBatsmanRuns(String batsmanRuns) {
        this.batsmanRuns = batsmanRuns;
    }

    public String getBatsmanBallsPlayed() {
        return batsmanBallsPlayed;
    }

    public void setBatsmanBallsPlayed(String batsmanBallsPlayed) {
        this.batsmanBallsPlayed = batsmanBallsPlayed;
    }

    public String getBatsmanFoursScored() {
        return batsmanFoursScored;
    }

    public void setBatsmanFoursScored(String batsmanFoursScored) {
        this.batsmanFoursScored = batsmanFoursScored;
    }

    public String getBatsmanSixesScored() {
        return batsmanSixesScored;
    }

    public void setBatsmanSixesScored(String batsmanSixesScored) {
        this.batsmanSixesScored = batsmanSixesScored;
    }

    public String getBatsmanStrikeRate() {
        return batsmanStrikeRate;
    }

    public void setBatsmanStrikeRate(String batsmanStrikeRate) {
        this.batsmanStrikeRate = batsmanStrikeRate;
    }

    public String getBallerOvers() {
        return ballerOvers;
    }

    public void setBallerOvers(String ballerOvers) {
        this.ballerOvers = ballerOvers;
    }

    public String getBallerMaidens() {
        return ballerMaidens;
    }

    public void setBallerMaidens(String ballerMaidens) {
        this.ballerMaidens = ballerMaidens;
    }

    public String getBallerRunsConceded() {
        return ballerRunsConceded;
    }

    public void setBallerRunsConceded(String ballerRunsConceded) {
        this.ballerRunsConceded = ballerRunsConceded;
    }

    public String getBallerWicketsTaken() {
        return ballerWicketsTaken;
    }

    public void setBallerWicketsTaken(String ballerWicketsTaken) {
        this.ballerWicketsTaken = ballerWicketsTaken;
    }

    public String getBallerEconomy() {
        return ballerEconomy;
    }

    public void setBallerEconomy(String ballerEconomy) {
        this.ballerEconomy = ballerEconomy;
    }

    @Override
    public String toString() {
        return "PlayerScorecard{" +
                "playerName='" + playerName + '\'' +
                ", batsmanRuns='" + batsmanRuns + '\'' +
                ", batsmanBallsPlayed='" + batsmanBallsPlayed + '\'' +
                ", batsmanFoursScored='" + batsmanFoursScored + '\'' +
                ", batsmanSixesScored='" + batsmanSixesScored + '\'' +
                ", batsmanStrikeRate='" + batsmanStrikeRate + '\'' +
                ", ballerOvers='" + ballerOvers + '\'' +
                ", ballerMaidens='" + ballerMaidens + '\'' +
                ", ballerRunsConceded='" + ballerRunsConceded + '\'' +
                ", ballerWicketsTaken='" + ballerWicketsTaken + '\'' +
                ", ballerEconomy='" + ballerEconomy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerScorecard that = (PlayerScorecard) o;
        return playerName.equals(that.playerName) && batsmanRuns.equals(that.batsmanRuns) && batsmanBallsPlayed.equals(that.batsmanBallsPlayed) && batsmanFoursScored.equals(that.batsmanFoursScored) && batsmanSixesScored.equals(that.batsmanSixesScored) && batsmanStrikeRate.equals(that.batsmanStrikeRate) && ballerOvers.equals(that.ballerOvers) && ballerMaidens.equals(that.ballerMaidens) && ballerRunsConceded.equals(that.ballerRunsConceded) && ballerWicketsTaken.equals(that.ballerWicketsTaken) && ballerEconomy.equals(that.ballerEconomy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, batsmanRuns, batsmanBallsPlayed, batsmanFoursScored, batsmanSixesScored, batsmanStrikeRate, ballerOvers, ballerMaidens, ballerRunsConceded, ballerWicketsTaken, ballerEconomy);
    }
}
