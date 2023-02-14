package com.example.scorecard.models;

import java.io.Serializable;

public class CricketTeammate implements Serializable {
    private static final long serialversionUID = 129348937L;
    private String playerName;
    private String age;
    private int runsScored;
    private int ballsPlayed;
    private int ballsBowled;
    private int runsConceded;
    private int foursScored;
    private int sixesScored;
    private int byesConceded;
    private int legByesConceded;
    private int widesConceded;
    private int noBallsConceded;
    private int wicketsTaken;
    private int maidensConceded;
    private int catchesTaken;
    private boolean isWicketKeeper;
    private boolean isCaptain;
    private boolean isViceCaptain;

    public CricketTeammate() {
    }

    public CricketTeammate(String playerName) {
        super();
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isWicketKeeper() {
        return isWicketKeeper;
    }

    public void setWicketKeeper(boolean wicketKeeper) {
        isWicketKeeper = wicketKeeper;
    }

    public boolean isCaptain() {
        return isCaptain;
    }

    public void setCaptain(boolean captain) {
        isCaptain = captain;
    }

    public boolean isViceCaptain() {
        return isViceCaptain;
    }

    public void setViceCaptain(boolean viceCaptain) {
        isViceCaptain = viceCaptain;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public void setBallsPlayed(int ballsPlayed) {
        this.ballsPlayed = ballsPlayed;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public void setBallsBowled(int ballsBowled) {
        this.ballsBowled = ballsBowled;
    }

    public int getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(int runsConceded) {
        this.runsConceded = runsConceded;
    }

    public int getByesConceded() {
        return byesConceded;
    }

    public void setByesConceded(int byesConceded) {
        this.byesConceded = byesConceded;
    }

    public int getLegByesConceded() {
        return legByesConceded;
    }

    public void setLegByesConceded(int legByesConceded) {
        this.legByesConceded = legByesConceded;
    }

    public int getWidesConceded() {
        return widesConceded;
    }

    public void setWidesConceded(int widesConceded) {
        this.widesConceded = widesConceded;
    }

    public int getNoBallsConceded() {
        return noBallsConceded;
    }

    public void setNoBallsConceded(int noBallsConceded) {
        this.noBallsConceded = noBallsConceded;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public int getCatchesTaken() {
        return catchesTaken;
    }

    public void setCatchesTaken(int catchesTaken) {
        this.catchesTaken = catchesTaken;
    }

    public int getFoursScored() {
        return foursScored;
    }

    public void setFoursScored(int foursScored) {
        this.foursScored = foursScored;
    }

    public int getSixesScored() {
        return sixesScored;
    }

    public void setSixesScored(int sixesScored) {
        this.sixesScored = sixesScored;
    }

    public int getMaidensConceded() {
        return maidensConceded;
    }

    public void setMaidensConceded(int maidensConceded) {
        this.maidensConceded = maidensConceded;
    }

    @Override
    public String toString() {
        return "CricketTeammate{" +
                "playerName='" + playerName + '\'' +
                ", age='" + age + '\'' +
                ", runsScored=" + runsScored +
                ", ballsPlayed=" + ballsPlayed +
                ", ballsBowled=" + ballsBowled +
                ", runsConceded=" + runsConceded +
                ", foursScored=" + foursScored +
                ", sixesScored=" + sixesScored +
                ", byesConceded=" + byesConceded +
                ", legByesConceded=" + legByesConceded +
                ", widesConceded=" + widesConceded +
                ", noBallsConceded=" + noBallsConceded +
                ", wicketsTaken=" + wicketsTaken +
                ", maidensConceded=" + maidensConceded +
                ", catchesTaken=" + catchesTaken +
                ", isWicketKeeper=" + isWicketKeeper +
                ", isCaptain=" + isCaptain +
                ", isViceCaptain=" + isViceCaptain +
                '}';
    }
}
