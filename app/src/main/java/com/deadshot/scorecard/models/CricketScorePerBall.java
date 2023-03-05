package com.deadshot.scorecard.models;

import java.util.Objects;

public class CricketScorePerBall {

    private int runsToAdd;
    private int ballsToAdd;
    private boolean isSix;
    private boolean isFour;
    private boolean isWicket;
    private int extraRuns;
    private boolean isLegBye;
    private boolean isBye;
    private boolean isNoBall;
    private boolean isWide;
    private boolean isBold;
    private boolean isLbw;
    private boolean isCatch;
    private boolean isRunOut;

    @Override
    public String toString() {
        return "CricketScorePerBall{" +
                "runsToAdd=" + runsToAdd +
                ", ballsToAdd=" + ballsToAdd +
                ", isSix=" + isSix +
                ", isFour=" + isFour +
                ", isWicket=" + isWicket +
                ", extraRuns=" + extraRuns +
                ", isLegBye=" + isLegBye +
                ", isBye=" + isBye +
                ", isNoBall=" + isNoBall +
                ", isWide=" + isWide +
                ", isBold=" + isBold +
                ", isLbw=" + isLbw +
                ", isCatch=" + isCatch +
                ", isRunOut=" + isRunOut +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CricketScorePerBall that = (CricketScorePerBall) o;
        return runsToAdd == that.runsToAdd && ballsToAdd == that.ballsToAdd && isSix == that.isSix && isFour == that.isFour && isWicket == that.isWicket && extraRuns == that.extraRuns && isLegBye == that.isLegBye && isBye == that.isBye && isNoBall == that.isNoBall && isWide == that.isWide && isBold == that.isBold && isLbw == that.isLbw && isCatch == that.isCatch && isRunOut == that.isRunOut;
    }

    @Override
    public int hashCode() {
        return Objects.hash(runsToAdd, ballsToAdd, isSix, isFour, isWicket, extraRuns, isLegBye, isBye, isNoBall, isWide, isBold, isLbw, isCatch, isRunOut);
    }

    public boolean isBye() {
        return isBye;
    }

    public void setBye(boolean bye) {
        isBye = bye;
    }

    public boolean isNoBall() {
        return isNoBall;
    }

    public void setNoBall(boolean noBall) {
        isNoBall = noBall;
    }

    public boolean isWide() {
        return isWide;
    }

    public void setWide(boolean wide) {
        isWide = wide;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public boolean isLbw() {
        return isLbw;
    }

    public void setLbw(boolean lbw) {
        isLbw = lbw;
    }

    public boolean isCatch() {
        return isCatch;
    }

    public void setCatch(boolean aCatch) {
        isCatch = aCatch;
    }

    public boolean isRunOut() {
        return isRunOut;
    }

    public void setRunOut(boolean runOut) {
        isRunOut = runOut;
    }

    public int getExtraRuns() {
        return extraRuns;
    }

    public void setExtraRuns(int extraRuns) {
        this.extraRuns = extraRuns;
    }

    public boolean isLegBye() {
        return isLegBye;
    }

    public void setLegBye(boolean legBye) {
        isLegBye = legBye;
    }

    public int getRunsToAdd() {
        return runsToAdd;
    }

    public void setRunsToAdd(int runsToAdd) {
        this.runsToAdd = runsToAdd;
    }

    public int getBallsToAdd() {
        return ballsToAdd;
    }

    public void setBallsToAdd(int ballsToAdd) {
        this.ballsToAdd = ballsToAdd;
    }

    public boolean isSix() {
        return isSix;
    }

    public void setSix(boolean six) {
        isSix = six;
    }

    public boolean isFour() {
        return isFour;
    }

    public void setFour(boolean four) {
        isFour = four;
    }

    public boolean isWicket() {
        return isWicket;
    }

    public void setWicket(boolean wicket) {
        isWicket = wicket;
    }

}
