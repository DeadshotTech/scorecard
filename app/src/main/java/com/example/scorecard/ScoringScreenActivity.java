package com.example.scorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.scorecard.models.CricketScorePerBall;
import com.example.scorecard.models.CricketTeammate;
import com.example.scorecard.models.MatchDetails;
import com.example.scorecard.utilities.BatsmanUtility;
import com.example.scorecard.utilities.BowlerUtility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ScoringScreenActivity extends AppCompatActivity {

    private static String activeBattingTeamName;
    private static List<CricketTeammate> activeBattingTeamList;
    private static List<CricketTeammate> activeBowlingTeamList;
    private static CricketTeammate activeBatsmanA = null, activeBatsmanB = null, activeBowler = null;
    private static MatchDetails matchDetails;
    private String matchDetailsReferencePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring_screen_activity);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            matchDetailsReferencePath = extras.get(CommonConstants.MATCH_DETAILS_RECORD_DETAILS_REFERENCE).toString();
        }

        configureFirebaseDataReadListeners();
        configureOnClickListeners();
    }

    private void configureOnClickListeners() {

        configureBoldOnClickListener();
        configureLbwOnClickListener();
        configureCatchOnClickListener();
        configureRunOutOnClickListener();
        configureWideOnClickListener();
        configureNoOnClickListener();
        configureByeOnClickListener();
        configureLegByeOnClickListener();
        configureZeroRunsOnClickListener();
        configureOneRunsOnClickListener();
        configureTwoRunsOnClickListener();
        configureThreeRunsOnClickListener();
        configureFourRunsOnClickListener();
        configureFiveRunsOnClickListener();
        configureSixRunsOnClickListener();

    }

    private void configureSixRunsOnClickListener() {

        TextView tvSixRunsScored = findViewById(R.id.scoring_screen_score_six);
        tvSixRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.SIX);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setSix(true);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void addRunsToScorecard(CricketScorePerBall cricketScorePerBall) {

        addRunsToActiveBatsman(cricketScorePerBall);
        addRunsToActiveBowler(cricketScorePerBall);
        addRunsToActiveBattingTeam(cricketScorePerBall);
        addBallsToActiveBowlingTeam(cricketScorePerBall);
        updateFirebaseData();
    }

    private void addBallsToActiveBowlingTeam(CricketScorePerBall cricketScorePerBall) {

    }

    private void addRunsToActiveBowler(CricketScorePerBall cricketScorePerBall) {

        activeBowler.setBallsBowled(activeBowler.getBallsBowled() + cricketScorePerBall.getBallsToAdd());
        activeBowler.setRunsConceded(activeBowler.getRunsConceded() + cricketScorePerBall.getRunsToAdd());

    }

    private void addRunsToActiveBattingTeam(CricketScorePerBall cricketScorePerBall) {

        if(matchDetails.getActiveBattingTeam().equals(CommonConstants.TEAM_A)){
            matchDetails.setTeamARuns(matchDetails.getTeamARuns() + cricketScorePerBall.getRunsToAdd());

        }
    }

    private void updateFirebaseData() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database
                .getReference(matchDetailsReferencePath);

        databaseReference.setValue(matchDetails);

    }

    private void addRunsToActiveBatsman(CricketScorePerBall cricketScorePerBall) {

//        TODO: Identify the active batsman here. Default palyer is used as batsman currently
        CricketTeammate activeBatsman = activeBatsmanA;
        activeBatsman.setRunsScored(activeBatsman.getRunsScored() +
                cricketScorePerBall.getRunsToAdd());
        activeBatsman.setSixesScored(activeBatsman.getSixesScored() +
                (cricketScorePerBall.isSix() ? CommonConstants.ONE : CommonConstants.ZERO));
        activeBatsman.setBallsPlayed(activeBatsman.getBallsPlayed() +
                cricketScorePerBall.getBallsToAdd());

//        TODO: Same logic to be applied as top to identify the active player
        activeBatsmanA = activeBatsman;

    }

    private void configureFiveRunsOnClickListener() {
        TextView tvSixRunsScored = findViewById(R.id.scoring_screen_score_five);
        tvSixRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.FIVE);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setSix(false);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureFourRunsOnClickListener() {
        TextView tvSixRunsScored = findViewById(R.id.scoring_screen_score_four);
        tvSixRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.FOUR);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setSix(false);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureThreeRunsOnClickListener() {
        TextView tvSixRunsScored = findViewById(R.id.scoring_screen_score_three);
        tvSixRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.THREE);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setSix(false);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureTwoRunsOnClickListener() {
        TextView tvSixRunsScored = findViewById(R.id.scoring_screen_score_two);
        tvSixRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.TWO);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setSix(false);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureOneRunsOnClickListener() {
        TextView tvSixRunsScored = findViewById(R.id.scoring_screen_score_one);
        tvSixRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setSix(false);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureZeroRunsOnClickListener() {
        TextView tvSixRunsScored = findViewById(R.id.scoring_screen_score_zero);
        tvSixRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.ZERO);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setSix(false);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureLegByeOnClickListener() {
    }

    private void configureByeOnClickListener() {
    }

    private void configureNoOnClickListener() {
    }

    private void configureWideOnClickListener() {
    }

    private void configureRunOutOnClickListener() {
    }

    private void configureCatchOnClickListener() {
    }

    private void configureLbwOnClickListener() {
    }

    private void configureBoldOnClickListener() {


    }

    private void configureFirebaseDataReadListeners() {

        configureMatchSummaryFirebaseDataRead(matchDetailsReferencePath);

    }

    private void configureMatchSummaryFirebaseDataRead(String matchDetailsReferencePath) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database
                .getReference(matchDetailsReferencePath);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                matchDetails = dataSnapshot.getValue(MatchDetails.class);

                loadMatchScoreCardData(matchDetails);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(CommonConstants.WARNING_LOG_TAG, "Failed to read value.", error.toException());
            }
        });

    }

    private void loadMatchScoreCardData(MatchDetails matchDetails) {

        activeBattingTeamName = matchDetails.getActiveBattingTeam();

//                TODO: As toss check is not added, a not nullable check is added. But this should also be handled by an exception.
        if(activeBattingTeamName != null && activeBattingTeamName.length() > 0){

            if(activeBattingTeamName.equalsIgnoreCase(CommonConstants.TEAM_A)) {
                activeBattingTeamList = matchDetails.getTeamATeammates();
                activeBowlingTeamList = matchDetails.getTeamBTeammates();
            }else{
                activeBattingTeamList = matchDetails.getTeamBTeammates();
                activeBowlingTeamList = matchDetails.getTeamATeammates();
            }

            activeBatsmanA = getActiveBatsman(activeBattingTeamList, null);
            activeBatsmanB = getActiveBatsman(activeBattingTeamList, activeBatsmanA);
            activeBowler = extractActiveBowler(activeBowlingTeamList);

            setDetailsForActiveBatsmanA(activeBatsmanA);
            setDetailsForActiveBatsmanB(activeBatsmanB);
            setDetailsForActiveBowler(activeBowler);

        }

    }

    private CricketTeammate getActiveBatsman(List<CricketTeammate> activeBattingTeamList, CricketTeammate existingActiveBatsman) {

        CricketTeammate activeBatsman = null;

        for(CricketTeammate cricketTeammate : activeBattingTeamList){
            if(cricketTeammate.isActiveBatsman() && !cricketTeammate.equals(existingActiveBatsman))
                activeBatsman = cricketTeammate;
        }

        return activeBatsman;

    }

    private CricketTeammate extractActiveBowler(List<CricketTeammate> activeBowlingTeamList) {

        CricketTeammate activeBowler = null;
        for(CricketTeammate cricketTeammate : activeBowlingTeamList){
            if(cricketTeammate.isActiveBowler()) {
                activeBowler = cricketTeammate;
                break;
            }
        }
        return activeBowler;
    }

    private void setDetailsForActiveBowler(CricketTeammate activeBowler) {

        String bowlerOvers = BowlerUtility.getBowlerOvers(activeBowler);

        TextView tvBowlerName = findViewById(R.id.scoring_screen_bowler_name);
        TextView tvBowlerRuns = findViewById(R.id.scoring_screen_bowler_runs_conceded);
        TextView tvBowlerOvers = findViewById(R.id.scoring_screen_bowler_overs_bowled);
        TextView tvBowlerWicketsTaken = findViewById(R.id.scoring_screen_bowler_wickets_taken);

        tvBowlerName.setText(activeBowler.getPlayerName());
        tvBowlerRuns.setText(activeBowler.getRunsConceded() + CommonConstants.EMPTY_STRING);
        tvBowlerOvers.setText(bowlerOvers);
        tvBowlerWicketsTaken.setText(activeBowler.getWicketsTaken() + CommonConstants.EMPTY_STRING);

    }

    private void setDetailsForActiveBatsmanB(CricketTeammate activeBatsmanB) {

        String batsmanStrikeRate = BatsmanUtility.getBatsmanStrikeRate(activeBatsmanB);

        TextView tvBatsmanName = findViewById(R.id.scoring_screen_batsman2_name);
        TextView tvBatsmanRuns = findViewById(R.id.scoring_screen_batsman2_runs);
        TextView tvBatsmanBallsPlayed = findViewById(R.id.scoring_screen_batsman2_balls);
        TextView tvBatsmanStrikeRate = findViewById(R.id.scoring_screen_batsman2_strike_rate);

        tvBatsmanName.setText(activeBatsmanB.getPlayerName());
        tvBatsmanRuns.setText(activeBatsmanB.getRunsScored() + CommonConstants.EMPTY_STRING);
        tvBatsmanBallsPlayed.setText(activeBatsmanB.getBallsPlayed() + CommonConstants.EMPTY_STRING);
        tvBatsmanStrikeRate.setText(batsmanStrikeRate);

    }

    private void setDetailsForActiveBatsmanA(CricketTeammate activeBatsmanA) {

        String batsmanStrikeRate = BatsmanUtility.getBatsmanStrikeRate(activeBatsmanA);

        TextView tvBatsmanName = findViewById(R.id.scoring_screen_batsman1_name);
        TextView tvBatsmanRuns = findViewById(R.id.scoring_screen_batsman1_runs);
        TextView tvBatsmanBallsPlayed = findViewById(R.id.scoring_screen_batsman1_balls);
        TextView tvBatsmanStrikeRate = findViewById(R.id.scoring_screen_batsman1_strike_rate);

        tvBatsmanName.setText(activeBatsmanA.getPlayerName());
        tvBatsmanRuns.setText(activeBatsmanA.getRunsScored() + CommonConstants.EMPTY_STRING);
        tvBatsmanBallsPlayed.setText(activeBatsmanA.getBallsPlayed() + CommonConstants.EMPTY_STRING);
        tvBatsmanStrikeRate.setText(batsmanStrikeRate);

    }

}