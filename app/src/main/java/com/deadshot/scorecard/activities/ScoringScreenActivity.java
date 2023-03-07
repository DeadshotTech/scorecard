package com.deadshot.scorecard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.deadshot.scorecard.R;
import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.models.CricketScorePerBall;
import com.deadshot.scorecard.models.CricketTeammate;
import com.deadshot.scorecard.models.MatchDetails;
import com.deadshot.scorecard.utilities.BatsmanUtility;
import com.deadshot.scorecard.utilities.BallerUtility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ScoringScreenActivity extends AppCompatActivity {

    private static String activeBattingTeamName;
    private static List<CricketTeammate> activeBattingTeamList;
    private static List<CricketTeammate> activeBallingTeamList;
    private static CricketTeammate activeBatsmanA = null, activeBatsmanB = null, activeBaller = null;
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
        addRunsToActiveBaller(cricketScorePerBall);
        addDetailsForActiveBattingTeam(cricketScorePerBall);
        addDetailsForActiveBallingTeam(cricketScorePerBall);
//        TODO: updateActivePlayer logic should be added here to move players around
        updateFirebaseData();
    }

    private void addDetailsForActiveBallingTeam(CricketScorePerBall cricketScorePerBall) {

        if(matchDetails.getActiveBattingTeam().equals(CommonConstants.TEAM_A)){
            matchDetails.setTeamBExtrasConceded(matchDetails.getTeamBExtrasConceded() +
                    cricketScorePerBall.getExtraRuns());
            matchDetails.setTeamBBallsBalled(matchDetails.getTeamBBallsBalled() +
                    cricketScorePerBall.getBallsToAdd());
        }else{
            matchDetails.setTeamAExtrasConceded(matchDetails.getTeamAExtrasConceded() +
                    cricketScorePerBall.getExtraRuns());
            matchDetails.setTeamABallsBalled(matchDetails.getTeamABallsBalled() +
                    cricketScorePerBall.getBallsToAdd());
        }
    }

    private void addRunsToActiveBaller(CricketScorePerBall cricketScorePerBall) {

        activeBaller.setBallsBalled(activeBaller.getBallsBalled() + cricketScorePerBall.getBallsToAdd());
        activeBaller.setRunsConceded(activeBaller.getRunsConceded() + cricketScorePerBall.getRunsToAdd());
        activeBaller.setExtrasConceded(activeBaller.getExtrasConceded() + cricketScorePerBall.getExtraRuns());
        activeBaller.setLegByesConceded(cricketScorePerBall.isLegBye() ?
                activeBaller.getLegByesConceded() + CommonConstants.ONE :
                activeBaller.getLegByesConceded());
        activeBaller.setByesConceded(cricketScorePerBall.isBye() ?
                activeBaller.getByesConceded() + CommonConstants.ONE :
                activeBaller.getByesConceded());
        activeBaller.setWidesConceded(cricketScorePerBall.isWide() ?
                activeBaller.getWidesConceded() + CommonConstants.ONE :
                activeBaller.getWidesConceded());
        activeBaller.setNoBallsConceded(cricketScorePerBall.isNoBall() ?
                activeBaller.getNoBallsConceded() + CommonConstants.ONE :
                activeBaller.getNoBallsConceded());
        activeBaller.setWicketsTaken(activeBaller.getWicketsTaken() + getWicketsToAdd(cricketScorePerBall));

    }

    private void addDetailsForActiveBattingTeam(CricketScorePerBall cricketScorePerBall) {

        if(matchDetails.getActiveBattingTeam().equals(CommonConstants.TEAM_A)){
            matchDetails.setTeamARuns(matchDetails.getTeamARuns() +
                    cricketScorePerBall.getRunsToAdd() +
                    cricketScorePerBall.getExtraRuns());
            matchDetails.setTeamAWickets(matchDetails.getTeamAWickets() + getWicketsToAdd(cricketScorePerBall));
        }else{
            matchDetails.setTeamBRuns(matchDetails.getTeamBRuns() +
                    cricketScorePerBall.getRunsToAdd() +
                    cricketScorePerBall.getExtraRuns());
            matchDetails.setTeamBWickets(matchDetails.getTeamBWickets() + getWicketsToAdd(cricketScorePerBall));
        }
    }

    private int getWicketsToAdd(CricketScorePerBall cricketScorePerBall) {
        if(cricketScorePerBall.isBold() ||
                cricketScorePerBall.isLbw() ||
                cricketScorePerBall.isCatch())
            return CommonConstants.ONE;

        return CommonConstants.ZERO;
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
        activeBatsman.setFoursScored(activeBatsman.getFoursScored() +
                (cricketScorePerBall.isFour() ? CommonConstants.ONE : CommonConstants.ZERO));
        activeBatsman.setBallsPlayed(activeBatsman.getBallsPlayed() +
                cricketScorePerBall.getBallsToAdd());

//        TODO: Same logic to be applied as top to identify the active player
        activeBatsmanA = activeBatsman;

    }

    private void configureFiveRunsOnClickListener() {
        TextView tvFiveRunsScored = findViewById(R.id.scoring_screen_score_five);
        tvFiveRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.FIVE);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureFourRunsOnClickListener() {
        TextView tvFourRunsScored = findViewById(R.id.scoring_screen_score_four);
        tvFourRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.FOUR);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureThreeRunsOnClickListener() {
        TextView tvThreeRunsScored = findViewById(R.id.scoring_screen_score_three);
        tvThreeRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.THREE);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureTwoRunsOnClickListener() {
        TextView tvTwoRunsScored = findViewById(R.id.scoring_screen_score_two);
        tvTwoRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.TWO);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureOneRunsOnClickListener() {
        TextView tvOneRunsScored = findViewById(R.id.scoring_screen_score_one);
        tvOneRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setRunsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureZeroRunsOnClickListener() {
        TextView tvZeroRunsScored = findViewById(R.id.scoring_screen_score_zero);
        tvZeroRunsScored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureLegByeOnClickListener() {
        TextView tvLegBye = findViewById(R.id.scoring_screen_score_lb);
        tvLegBye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
//                TODO: New buttons need to be added to get the runs to be added
                cricketScorePerBall.setExtraRuns(CommonConstants.ONE);
                cricketScorePerBall.setLegBye(true);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureByeOnClickListener() {
        TextView tvByes = findViewById(R.id.scoring_screen_score_bye);
        tvByes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
//                TODO: New buttons need to be added to get the runs to be added
                cricketScorePerBall.setExtraRuns(CommonConstants.ONE);
                cricketScorePerBall.setBye(true);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureNoOnClickListener() {
        TextView tvNoBall = findViewById(R.id.scoring_screen_score_no);
        tvNoBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
//                TODO: Fetch runs for user for cases where there can be dual behavior
                cricketScorePerBall.setRunsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setNoBall(true);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureWideOnClickListener() {
        TextView tvWideBall = findViewById(R.id.scoring_screen_score_wide);
        tvWideBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
//                TODO: New buttons need to be added to get the runs to be added
                cricketScorePerBall.setExtraRuns(CommonConstants.ONE);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureRunOutOnClickListener() {
        TextView tvRunOut = findViewById(R.id.scoring_screen_score_run_out);
        tvRunOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
//                TODO: Fetch runs for user for cases where there can be dual behavior
                cricketScorePerBall.setRunsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setRunOut(true);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureCatchOnClickListener() {
        TextView tvCatchOut = findViewById(R.id.scoring_screen_score_catch);
        tvCatchOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
//                TODO: Fetch runs for user for cases where there can be dual behavior
                cricketScorePerBall.setRunsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setCatch(true);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureLbwOnClickListener() {
        TextView tvLbw = findViewById(R.id.scoring_screen_score_lbw);
        tvLbw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setLbw(true);
                addRunsToScorecard(cricketScorePerBall);
            }
        });
    }

    private void configureBoldOnClickListener() {
        TextView tvBold = findViewById(R.id.scoring_screen_score_bold);
        tvBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CricketScorePerBall cricketScorePerBall = new CricketScorePerBall();
                cricketScorePerBall.setBallsToAdd(CommonConstants.ONE);
                cricketScorePerBall.setBold(true);
                addRunsToScorecard(cricketScorePerBall);
            }
        });

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
                Log.d(CommonConstants.INFO_LOG_TAG, matchDetails.toString());
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
                activeBallingTeamList = matchDetails.getTeamBTeammates();
            }else{
                activeBattingTeamList = matchDetails.getTeamBTeammates();
                activeBallingTeamList = matchDetails.getTeamATeammates();
            }

            activeBatsmanA = getActiveBatsman(activeBattingTeamList, null);
            activeBatsmanB = getActiveBatsman(activeBattingTeamList, activeBatsmanA);
            activeBaller = extractActiveBaller(activeBallingTeamList);

            setDetailsForActiveBatsmanA(activeBatsmanA);
            setDetailsForActiveBatsmanB(activeBatsmanB);
            setDetailsForActiveBaller(activeBaller);

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

    private CricketTeammate extractActiveBaller(List<CricketTeammate> activeBallingTeamList) {

        CricketTeammate activeBaller = null;
        for(CricketTeammate cricketTeammate : activeBallingTeamList){
            if(cricketTeammate.isActiveBaller()) {
                activeBaller = cricketTeammate;
                break;
            }
        }
        return activeBaller;
    }

    private void setDetailsForActiveBaller(CricketTeammate activeBaller) {

        String ballerOvers = BallerUtility.getBallerOvers(activeBaller);

        TextView tvBallerName = findViewById(R.id.scoring_screen_baller_name);
        TextView tvBallerRuns = findViewById(R.id.scoring_screen_baller_runs_conceded);
        TextView tvBallerOvers = findViewById(R.id.scoring_screen_baller_overs_balled);
        TextView tvBallerWicketsTaken = findViewById(R.id.scoring_screen_baller_wickets_taken);

        tvBallerName.setText(activeBaller.getPlayerName());
        tvBallerRuns.setText(activeBaller.getRunsConceded() + CommonConstants.EMPTY_STRING);
        tvBallerOvers.setText(ballerOvers);
        tvBallerWicketsTaken.setText(activeBaller.getWicketsTaken() + CommonConstants.EMPTY_STRING);

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