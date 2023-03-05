package com.example.scorecard.models;

import com.example.scorecard.CommonConstants;
import com.google.android.gms.common.internal.service.Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MatchDetails implements Serializable {
    private static final long serialversionUID = 129348938L;
    private String teamAName;
    private String teamBName;
    private String dateOfMatch;
    private int teamARuns;
    private int teamBRuns;
    private int teamAWickets;
    private int teamBWickets;
    List<CricketTeammate> teamATeammates;
    List<CricketTeammate> teamBTeammates;
    private String activeBattingTeam;
    private String activeBowlingTeam;

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

    public String getActiveBowlingTeam() {
        return activeBowlingTeam;
    }

    public void setActiveBowlingTeam(String activeBowlingTeam) {
        this.activeBowlingTeam = activeBowlingTeam;
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
                ", teamATeammates=" + teamATeammates +
                ", teamBTeammates=" + teamBTeammates +
                ", activeBattingTeam='" + activeBattingTeam + '\'' +
                ", activeBowlingTeam='" + activeBowlingTeam + '\'' +
                '}';
    }
}
