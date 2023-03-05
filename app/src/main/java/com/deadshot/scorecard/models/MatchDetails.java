package com.deadshot.scorecard.models;

import com.deadshot.scorecard.constants.CommonConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MatchDetails implements Serializable {
    private static final long serialversionUID = 129348938L;
    private String teamAName;
    private String teamBName;
    private String dateOfMatch;
    private int teamARuns;
    private int teamBRuns;
    private int teamAWickets;
    private int teamBWickets;
    private int teamABallsBalled;
    private int teamBBallsBalled;
    private int teamAExtrasConceded;
    private int teamBExtrasConceded;
    List<CricketTeammate> teamATeammates;
    List<CricketTeammate> teamBTeammates;
    private String activeBattingTeam;
    private String activeBallingTeam;
    private int maxNumberOfBalls;

    public MatchDetails(){

        setTeamAName(CommonConstants.EMPTY_STRING);
        setTeamBName(CommonConstants.EMPTY_STRING);
        setDateOfMatch(CommonConstants.EMPTY_STRING);
        setTeamARuns(0);
        setTeamBRuns(0);
        setTeamAWickets(0);
        setTeamBWickets(0);
        setTeamATeammates(new ArrayList<>());
        setTeamBTeammates(new ArrayList<>());
        setActiveBattingTeam(CommonConstants.EMPTY_STRING);

    }

    @Override
    public String toString() {
        return "MatchDetails{" +
                "teamAName='" + teamAName + '\'' +
                ", teamBName='" + teamBName + '\'' +
                ", dateOfMatch='" + dateOfMatch + '\'' +
                ", teamARuns=" + teamARuns +
                ", teamBRuns=" + teamBRuns +
                ", teamAWickets=" + teamAWickets +
                ", teamBWickets=" + teamBWickets +
                ", teamABallsBalled=" + teamABallsBalled +
                ", teamBBallsBalled=" + teamBBallsBalled +
                ", teamAExtrasConceded=" + teamAExtrasConceded +
                ", teamBExtrasConceded=" + teamBExtrasConceded +
                ", teamATeammates=" + teamATeammates +
                ", teamBTeammates=" + teamBTeammates +
                ", activeBattingTeam='" + activeBattingTeam + '\'' +
                ", activeBallingTeam='" + activeBallingTeam + '\'' +
                ", maxNumberOfBalls=" + maxNumberOfBalls +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchDetails that = (MatchDetails) o;
        return teamARuns == that.teamARuns && teamBRuns == that.teamBRuns && teamAWickets == that.teamAWickets && teamBWickets == that.teamBWickets && teamABallsBalled == that.teamABallsBalled && teamBBallsBalled == that.teamBBallsBalled && teamAExtrasConceded == that.teamAExtrasConceded && teamBExtrasConceded == that.teamBExtrasConceded && maxNumberOfBalls == that.maxNumberOfBalls && teamAName.equals(that.teamAName) && teamBName.equals(that.teamBName) && dateOfMatch.equals(that.dateOfMatch) && teamATeammates.equals(that.teamATeammates) && teamBTeammates.equals(that.teamBTeammates) && activeBattingTeam.equals(that.activeBattingTeam) && activeBallingTeam.equals(that.activeBallingTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamAName, teamBName, dateOfMatch, teamARuns, teamBRuns, teamAWickets, teamBWickets, teamABallsBalled, teamBBallsBalled, teamAExtrasConceded, teamBExtrasConceded, teamATeammates, teamBTeammates, activeBattingTeam, activeBallingTeam, maxNumberOfBalls);
    }

    public int getMaxNumberOfBalls() {
        return maxNumberOfBalls;
    }

    public void setMaxNumberOfBalls(int maxNumberOfBalls) {
        this.maxNumberOfBalls = maxNumberOfBalls;
    }

    public int getTeamABallsBalled() {
        return teamABallsBalled;
    }

    public void setTeamABallsBalled(int teamABallsBalled) {
        this.teamABallsBalled = teamABallsBalled;
    }

    public int getTeamBBallsBalled() {
        return teamBBallsBalled;
    }

    public void setTeamBBallsBalled(int teamBBallsBalled) {
        this.teamBBallsBalled = teamBBallsBalled;
    }

    public int getTeamAExtrasConceded() {
        return teamAExtrasConceded;
    }

    public void setTeamAExtrasConceded(int teamAExtrasConceded) {
        this.teamAExtrasConceded = teamAExtrasConceded;
    }

    public int getTeamBExtrasConceded() {
        return teamBExtrasConceded;
    }

    public void setTeamBExtrasConceded(int teamBExtrasConceded) {
        this.teamBExtrasConceded = teamBExtrasConceded;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName;
    }

    public String getDateOfMatch() {
        return dateOfMatch;
    }

    public void setDateOfMatch(String dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
    }

    public List<CricketTeammate> getTeamATeammates() {
        return teamATeammates;
    }

    public void setTeamATeammates(List<CricketTeammate> teamATeammates) {
        this.teamATeammates = teamATeammates;
    }

    public List<CricketTeammate> getTeamBTeammates() {
        return teamBTeammates;
    }

    public void setTeamBTeammates(List<CricketTeammate> teamBTeammates) {
        this.teamBTeammates = teamBTeammates;
    }

    public int getTeamARuns() {
        return teamARuns;
    }

    public void setTeamARuns(int teamARuns) {
        this.teamARuns = teamARuns;
    }

    public int getTeamBRuns() {
        return teamBRuns;
    }

    public void setTeamBRuns(int teamBRuns) {
        this.teamBRuns = teamBRuns;
    }

    public int getTeamAWickets() {
        return teamAWickets;
    }

    public void setTeamAWickets(int teamAWickets) {
        this.teamAWickets = teamAWickets;
    }

    public int getTeamBWickets() {
        return teamBWickets;
    }

    public void setTeamBWickets(int teamBWickets) {
        this.teamBWickets = teamBWickets;
    }

    public String getActiveBattingTeam() {
        return activeBattingTeam;
    }

    public void setActiveBattingTeam(String activeBattingTeam) {
        this.activeBattingTeam = activeBattingTeam;
    }

    public String getActiveBallingTeam() {
        return activeBallingTeam;
    }

    public void setActiveBallingTeam(String activeBallingTeam) {
        this.activeBallingTeam = activeBallingTeam;
    }

}
