package com.example.scorecard.models;

import java.util.Objects;

public class CricketScorePerBall {

    private int runsToAdd;
    private int ballsToAdd;
    private boolean isSix;
    private boolean isFour;
    private boolean isWicket;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CricketScorePerBall that = (CricketScorePerBall) o;
        return runsToAdd == that.runsToAdd && ballsToAdd == that.ballsToAdd && isSix == that.isSix && isFour == that.isFour && isWicket == that.isWicket;
    }

    @Override
    public int hashCode() {
        return Objects.hash(runsToAdd, ballsToAdd, isSix, isFour, isWicket);
    }

    @Override
    public String toString() {
        return "CricketScorePerBall{" +
                "runsToAdd=" + runsToAdd +
                ", ballsToAdd=" + ballsToAdd +
                ", isSix=" + isSix +
                ", isFour=" + isFour +
                ", isWicket=" + isWicket +
                '}';
    }
}
