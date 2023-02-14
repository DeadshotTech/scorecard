package com.example.scorecard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scorecard.adapters.PlayerBattingScoreCardAdapter;
import com.example.scorecard.adapters.PlayerBowlingScoreCardAdapter;
import com.example.scorecard.models.CricketTeammate;
import com.example.scorecard.models.MatchDetails;

import java.util.ArrayList;
import java.util.List;

public class ScorecardActivity extends AppCompatActivity {

    private MatchDetails matchDetails;
    private PlayerBattingScoreCardAdapter playerBattingScoreCardAdapter;
    private PlayerBowlingScoreCardAdapter playerBowlingScoreCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            matchDetails = (MatchDetails) extras.get(CommonConstants.MATCH_DETAILS);
        }

        setupViews();
        setupOnClickListeners();

    }

    private void setupOnClickListeners() {

        setupOnClickListenersForTeamA();
        setupOnClickListenersForTeamB();

    }

    private void setupOnClickListenersForTeamB() {

        LinearLayout linearLayout = findViewById(R.id.scorecard_team_b_controller);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpTeamBattingScoreCardForTeamB();
                setUpTeamBowlingScoreCardForTeamB();
            }
        });
    }

    private void setupOnClickListenersForTeamA() {

        LinearLayout linearLayout = findViewById(R.id.scorecard_team_a_controller);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpTeamBattingScoreCardForTeamA();
                setUpTeamBowlingScoreCardForTeamA();
            }
        });
    }

    private void setupViews() {

        configureTeamScoreCard();
        configureTeamBattingScoreCard();
        configureTeamBowlingScoreCard();

    }

    private void configureTeamBowlingScoreCard() {

        RecyclerView rvPlayerBowlingScorecard = (RecyclerView) findViewById(R.id.scorecard_player_bowling_stats);
        rvPlayerBowlingScorecard.setNestedScrollingEnabled(false);
        List<CricketTeammate> bowlingScorecard = new ArrayList<>();

        playerBowlingScoreCardAdapter = new PlayerBowlingScoreCardAdapter(bowlingScorecard);
        rvPlayerBowlingScorecard.setAdapter(playerBowlingScoreCardAdapter);
        rvPlayerBowlingScorecard.setLayoutManager(new LinearLayoutManager(this));

        setUpTeamBowlingScoreCardForTeamA();
    }

    private void setUpTeamBowlingScoreCardForTeamA() {

        List<CricketTeammate> teamABowlingScorecard = new ArrayList<>();
        teamABowlingScorecard.add(getHeaderDataForBowlingScorecard());
        teamABowlingScorecard.addAll(matchDetails.getTeamATeammates());
        playerBowlingScoreCardAdapter.setAdapterData(teamABowlingScorecard);
        playerBowlingScoreCardAdapter.notifyDataSetChanged();

    }

    private void setUpTeamBowlingScoreCardForTeamB() {

        List<CricketTeammate> teamBBowlingScorecard = new ArrayList<>();
        teamBBowlingScorecard.add(getHeaderDataForBowlingScorecard());
        teamBBowlingScorecard.addAll(matchDetails.getTeamBTeammates());
        playerBowlingScoreCardAdapter.setAdapterData(teamBBowlingScorecard);
        playerBowlingScoreCardAdapter.notifyDataSetChanged();

    }

    private CricketTeammate getHeaderDataForBowlingScorecard() {

        CricketTeammate headerDataForBowling = new CricketTeammate();
        headerDataForBowling.setPlayerName("Bowling");
        headerDataForBowling.setBallsBowled(1);
        headerDataForBowling.setMaidensConceded(2);
        headerDataForBowling.setRunsConceded(3);
        headerDataForBowling.setWicketsTaken(4);

        return headerDataForBowling;
    }

    private void configureTeamBattingScoreCard() {

        RecyclerView rvPlayerBattingScorecard = (RecyclerView) findViewById(R.id.scorecard_player_batting_stats);
        rvPlayerBattingScorecard.setNestedScrollingEnabled(false);
        List<CricketTeammate> battingScorecard = new ArrayList<>();

        playerBattingScoreCardAdapter = new PlayerBattingScoreCardAdapter(battingScorecard);
        rvPlayerBattingScorecard.setAdapter(playerBattingScoreCardAdapter);
        rvPlayerBattingScorecard.setLayoutManager(new LinearLayoutManager(this));

        setUpTeamBattingScoreCardForTeamA();

    }

    private void setUpTeamBattingScoreCardForTeamA() {

        List<CricketTeammate> teamABattingScorecard = new ArrayList<>();
        teamABattingScorecard.add(getHeaderDataForBattingScorecard());
        teamABattingScorecard.addAll(matchDetails.getTeamATeammates());
        playerBattingScoreCardAdapter.setAdapterData(teamABattingScorecard);
        playerBattingScoreCardAdapter.notifyDataSetChanged();

    }

    private void setUpTeamBattingScoreCardForTeamB() {

        List<CricketTeammate> teamBBattingScorecard = new ArrayList<>();
        teamBBattingScorecard.add(getHeaderDataForBattingScorecard());
        teamBBattingScorecard.addAll(matchDetails.getTeamBTeammates());
        playerBattingScoreCardAdapter.setAdapterData(teamBBattingScorecard);
        playerBattingScoreCardAdapter.notifyDataSetChanged();

    }

    private CricketTeammate getHeaderDataForBattingScorecard() {

        CricketTeammate headerDataForBatting = new CricketTeammate();
        headerDataForBatting.setPlayerName("Batting");
        headerDataForBatting.setRunsScored(1);
        headerDataForBatting.setBallsPlayed(2);
        headerDataForBatting.setFoursScored(3);
        headerDataForBatting.setSixesScored(4);

        return headerDataForBatting;

    }

    private void configureTeamScoreCard() {

        String teamAScore = matchDetails.getTeamARuns() +
                CommonConstants.SCORE_SEPERATOR +
                matchDetails.getTeamAWickets();
        String teamBScore = matchDetails.getTeamBRuns() +
                CommonConstants.SCORE_SEPERATOR +
                matchDetails.getTeamBWickets();

        TextView tvTeamAName = findViewById(R.id.scorecard_team_a_name);
        TextView tvTeamBName = findViewById(R.id.scorecard_team_b_name);
        TextView tvTeamAScore = findViewById(R.id.scorecard_team_a_score);
        TextView tvTeamBScore = findViewById(R.id.scorecard_team_b_score);

        tvTeamAName.setText(matchDetails.getTeamAName());
        tvTeamBName.setText(matchDetails.getTeamBName());
        tvTeamAScore.setText(teamAScore);
        tvTeamBScore.setText(teamBScore);

    }


}