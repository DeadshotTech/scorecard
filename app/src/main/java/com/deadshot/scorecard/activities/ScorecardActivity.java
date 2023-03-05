package com.deadshot.scorecard.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.R;
import com.deadshot.scorecard.adapters.PlayerBattingScoreCardAdapter;
import com.deadshot.scorecard.adapters.PlayerBallingScoreCardAdapter;
import com.deadshot.scorecard.models.CricketTeammate;
import com.deadshot.scorecard.models.MatchDetails;
import com.deadshot.scorecard.models.adapter.PlayerScorecard;
import com.deadshot.scorecard.utilities.CommonUtility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ScorecardActivity extends AppCompatActivity {

    private MatchDetails matchDetails;
    private PlayerBattingScoreCardAdapter playerBattingScoreCardAdapter;
    private PlayerBallingScoreCardAdapter playerBallingScoreCardAdapter;

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
        setupOnClickListenersForRecordGame();
    }

    private void setupOnClickListenersForRecordGame() {

        Button bnRecordGame = findViewById(R.id.scorecard_record_game);
        bnRecordGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                TODO: Extra bundles to indicate path of the game should be passed before enabling it.
                Intent intent = new Intent(ScorecardActivity.this, ScoringScreenActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setupOnClickListenersForTeamB() {

        LinearLayout linearLayout = findViewById(R.id.scorecard_team_b_controller);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpTeamBattingScoreCardForTeamB();
                setUpTeamBallingScoreCardForTeamA();
            }
        });
    }

    private void setupOnClickListenersForTeamA() {

        LinearLayout linearLayout = findViewById(R.id.scorecard_team_a_controller);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpTeamBattingScoreCardForTeamA();
                setUpTeamBallingScoreCardForTeamB();
            }
        });
    }

    private void setupViews() {

        configureTeamScoreCard();
        configureTeamBattingScoreCard();
        configureTeamBallingScoreCard();

    }

    private void configureTeamBallingScoreCard() {

        RecyclerView rvPlayerBallingScorecard = (RecyclerView) findViewById(R.id.scorecard_player_balling_stats);
        rvPlayerBallingScorecard.setNestedScrollingEnabled(false);
        List<PlayerScorecard> ballingScorecard = new ArrayList<>();

        playerBallingScoreCardAdapter = new PlayerBallingScoreCardAdapter(ballingScorecard);
        rvPlayerBallingScorecard.setAdapter(playerBallingScoreCardAdapter);
        rvPlayerBallingScorecard.setLayoutManager(new LinearLayoutManager(this));

        setUpTeamBallingScoreCardForTeamB();
    }

    private void setUpTeamBallingScoreCardForTeamA() {

        List<PlayerScorecard> teamABallingScorecard = new ArrayList<>();
        teamABallingScorecard.add(getHeaderDataForBallingScorecard());
        teamABallingScorecard.addAll(CommonUtility.getScorecardDetailsForAdapter(
                matchDetails.getTeamATeammates()
                        .stream()
                        .filter(CricketTeammate::verifyIfBallerHasBalled)
                        .collect(Collectors.toList())
        ));
        playerBallingScoreCardAdapter.setAdapterData(teamABallingScorecard);
        playerBallingScoreCardAdapter.notifyDataSetChanged();

    }

    private void setUpTeamBallingScoreCardForTeamB() {

        List<PlayerScorecard> teamBBallingScorecard = new ArrayList<>();
        teamBBallingScorecard.add(getHeaderDataForBallingScorecard());
        teamBBallingScorecard.addAll(CommonUtility.getScorecardDetailsForAdapter(
                matchDetails.getTeamBTeammates()
                        .stream()
                        .filter(CricketTeammate::verifyIfBallerHasBalled)
                        .collect(Collectors.toList())
        ));
        playerBallingScoreCardAdapter.setAdapterData(teamBBallingScorecard);
        playerBallingScoreCardAdapter.notifyDataSetChanged();

    }

    private PlayerScorecard getHeaderDataForBallingScorecard() {

        PlayerScorecard headerDataForBalling = new PlayerScorecard();
        headerDataForBalling.setPlayerName(CommonConstants.PLAYER_NAME_HEADER);
        headerDataForBalling.setBallerOvers(CommonConstants.OVER_HEADER);
        headerDataForBalling.setBallerMaidens(CommonConstants.MAIDENS_HEADER);
        headerDataForBalling.setBallerRunsConceded(CommonConstants.RUNS_HEADER);
        headerDataForBalling.setBallerEconomy(CommonConstants.ECONOMY_HEADER);
        headerDataForBalling.setBallerWicketsTaken(CommonConstants.WICKETS_HEADER);

        return headerDataForBalling;
    }

    private void configureTeamBattingScoreCard() {

        RecyclerView rvPlayerBattingScorecard = (RecyclerView) findViewById(R.id.scorecard_player_batting_stats);
        rvPlayerBattingScorecard.setNestedScrollingEnabled(false);
        List<PlayerScorecard> battingScorecard = new ArrayList<>();

        playerBattingScoreCardAdapter = new PlayerBattingScoreCardAdapter(battingScorecard);
        rvPlayerBattingScorecard.setAdapter(playerBattingScoreCardAdapter);
        rvPlayerBattingScorecard.setLayoutManager(new LinearLayoutManager(this));

        setUpTeamBattingScoreCardForTeamA();

    }

    private void setUpTeamBattingScoreCardForTeamA() {

        List<PlayerScorecard> teamABattingScorecard = new ArrayList<>();
        teamABattingScorecard.add(getHeaderDataForBattingScorecard());
        teamABattingScorecard.addAll(CommonUtility.getScorecardDetailsForAdapter(matchDetails.getTeamATeammates()));
        playerBattingScoreCardAdapter.setAdapterData(teamABattingScorecard);
        playerBattingScoreCardAdapter.notifyDataSetChanged();

    }

    private void setUpTeamBattingScoreCardForTeamB() {

        List<PlayerScorecard> teamBBattingScorecard = new ArrayList<>();
        teamBBattingScorecard.add(getHeaderDataForBattingScorecard());
        teamBBattingScorecard.addAll(CommonUtility.getScorecardDetailsForAdapter(matchDetails.getTeamBTeammates()));
        playerBattingScoreCardAdapter.setAdapterData(teamBBattingScorecard);
        playerBattingScoreCardAdapter.notifyDataSetChanged();

    }

    private PlayerScorecard getHeaderDataForBattingScorecard() {

        PlayerScorecard headerDataForBatting = new PlayerScorecard();
        headerDataForBatting.setPlayerName(CommonConstants.PLAYER_NAME_HEADER);
        headerDataForBatting.setBatsmanRuns(CommonConstants.RUNS_HEADER);
        headerDataForBatting.setBatsmanFoursScored(CommonConstants.FOURS_HEADER);
        headerDataForBatting.setBatsmanSixesScored(CommonConstants.SIXES_HEADER);
        headerDataForBatting.setBatsmanStrikeRate(CommonConstants.STRIKE_RATE_HEADER);
        headerDataForBatting.setBatsmanBallsPlayed(CommonConstants.BALLS_HEADER);

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