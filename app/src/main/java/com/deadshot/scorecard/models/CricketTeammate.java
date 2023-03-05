package com.deadshot.scorecard.models;

import com.deadshot.scorecard.constants.CommonConstants;

import java.io.Serializable;
import java.util.Objects;

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
    private boolean isActiveBatsman;
    private boolean isActiveBowler;
    private int extrasConceded;

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
                ", isActiveBatsman=" + isActiveBatsman +
                ", isActiveBowler=" + isActiveBowler +
                ", extrasConceded=" + extrasConceded +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CricketTeammate that = (CricketTeammate) o;
        return runsScored == that.runsScored && ballsPlayed == that.ballsPlayed && ballsBowled == that.ballsBowled && runsConceded == that.runsConceded && foursScored == that.foursScored && sixesScored == that.sixesScored && byesConceded == that.byesConceded && legByesConceded == that.legByesConceded && widesConceded == that.widesConceded && noBallsConceded == that.noBallsConceded && wicketsTaken == that.wicketsTaken && maidensConceded == that.maidensConceded && catchesTaken == that.catchesTaken && isWicketKeeper == that.isWicketKeeper && isCaptain == that.isCaptain && isViceCaptain == that.isViceCaptain && isActiveBatsman == that.isActiveBatsman && isActiveBowler == that.isActiveBowler && extrasConceded == that.extrasConceded && Objects.equals(playerName, that.playerName) && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, age, runsScored, ballsPlayed, ballsBowled, runsConceded, foursScored, sixesScored, byesConceded, legByesConceded, widesConceded, noBallsConceded, wicketsTaken, maidensConceded, catchesTaken, isWicketKeeper, isCaptain, isViceCaptain, isActiveBatsman, isActiveBowler, extrasConceded);
    }

    public int getExtrasConceded() {
        return extrasConceded;
    }

    public void setExtrasConceded(int extrasConceded) {
        this.extrasConceded = extrasConceded;
    }

    public CricketTeammate() {

        setPlayerName(CommonConstants.EMPTY_STRING);
        setActiveBowler(false);
        setActiveBatsman(false);
        setMaidensConceded(0);
        setWicketsTaken(0);
        setBallsBowled(0);
        setSixesScored(0);
        setFoursScored(0);
        setRunsConceded(0);
        setBallsPlayed(0);
        setBallsBowled(0);
        setRunsScored(0);
        setAge(CommonConstants.EMPTY_STRING);
        setByesConceded(0);
        setWidesConceded(0);
        setRunsConceded(0);
        setLegByesConceded(0);
        setNoBallsConceded(0);
        setWicketKeeper(false);
        setCaptain(false);
        setViceCaptain(false);

    }

    public boolean verifyIfBowlerHasBowled(){
        return (ballsBowled > 0 ||
                widesConceded > 0 ||
                noBallsConceded > 0 );
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

    public boolean isActiveBatsman() {
        return isActiveBatsman;
    }

    public void setActiveBatsman(boolean activeBatsman) {
        isActiveBatsman = activeBatsman;
    }

    public boolean isActiveBowler() {
        return isActiveBowler;
    }

    public void setActiveBowler(boolean activeBowler) {
        isActiveBowler = activeBowler;
    }

}
