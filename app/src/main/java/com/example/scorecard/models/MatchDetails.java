package com.example.scorecard.models;

import java.io.Serializable;
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

    public MatchDetails(){

    }

    public MatchDetails(String teamAName, String teamBName, String dateOfMatch, int teamARuns, int teamBRuns, int teamAWickets, int teamBWickets, List<CricketTeammate> teamATeammates, List<CricketTeammate> teamBTeammates) {
        this.teamAName = teamAName;
        this.teamBName = teamBName;
        this.dateOfMatch = dateOfMatch;
        this.teamARuns = teamARuns;
        this.teamBRuns = teamBRuns;
        this.teamAWickets = teamAWickets;
        this.teamBWickets = teamBWickets;
        this.teamATeammates = teamATeammates;
        this.teamBTeammates = teamBTeammates;
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
                '}';
    }
}
