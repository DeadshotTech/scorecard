package com.deadshot.scorecard.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deadshot.scorecard.R;
import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.models.MatchDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TossActivity extends AppCompatActivity {

    private String matchDetailsReferencePath;
    private String teamAName;
    private String teamBName;
    private String activeBattingTeam;
    private String activeBallingTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toss);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            teamAName = extras.get(CommonConstants.TEAM_A).toString();
            teamBName = extras.get(CommonConstants.TEAM_B).toString();
            matchDetailsReferencePath = extras.get(CommonConstants.MATCH_DETAILS_RECORD_DETAILS_REFERENCE).toString();
        }

//        TODO: Remove the code for reading Match Details as it is not really required here. Added for debugging
        readMatchDetails();
        setupOnClickListeners();

    }

    private void readMatchDetails(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference(matchDetailsReferencePath);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MatchDetails matchDetails = snapshot.getValue(MatchDetails.class);
                Log.d(CommonConstants.INFO_LOG_TAG, matchDetails.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupOnClickListeners() {

        setupTossButtonOnClickListener();
        setupTossPreferenceBattingListener();
        setupTossPreferenceBallingListener();

    }

    private void setupTossPreferenceBallingListener() {
        ImageView ivBallingPreference = (ImageView) findViewById(R.id.toss_winner_preference_baller);
        saveDetailsStartMatch(ivBallingPreference, false);
    }

    private void setupTossPreferenceBattingListener() {
        ImageView ivBattingPreference = (ImageView) findViewById(R.id.toss_winner_preference_batting);
        saveDetailsStartMatch(ivBattingPreference, true);
    }

    private void saveDetailsStartMatch(View preference, boolean isPreferenceBatting){
        preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPreferenceBatting) {
                    activeBattingTeam = switchActiveTeam(activeBattingTeam);
                    activeBallingTeam = switchActiveTeam(activeBallingTeam);
                }
                updateActiveBattingTeamFirebase();
            }
        });
    }

    private String switchActiveTeam(String activeTeam) {
        return CommonConstants.TEAM_A.equals(activeTeam) ?
                CommonConstants.TEAM_B :
                CommonConstants.TEAM_A;
    }

    private void navigateToScoringScreen() {
        Intent intent = new Intent(TossActivity.this, ScoringScreenActivity.class);
        intent.putExtra(CommonConstants.MATCH_DETAILS_RECORD_DETAILS_REFERENCE, matchDetailsReferencePath);
        intent.putExtra(CommonConstants.ACTIVE_BATTING_TEAM_FIREBASE_REFERENCE, activeBattingTeam);
        intent.putExtra(CommonConstants.ACTIVE_BALLING_TEAM_FIREBASE_REFERENCE, activeBallingTeam);
        startActivity(intent);
    }

    private void updateActiveBattingTeamFirebase() {
        Log.i(CommonConstants.INFO_LOG_TAG, activeBattingTeam);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference(matchDetailsReferencePath);

        Map<String, Object> activeTeams = new HashMap<>();
        activeTeams.put(CommonConstants.ACTIVE_BATTING_TEAM_FIREBASE_REFERENCE, activeBattingTeam);
        activeTeams.put(CommonConstants.ACTIVE_BALLING_TEAM_FIREBASE_REFERENCE, activeBallingTeam);
        databaseReference.updateChildren(activeTeams)
//        databaseReference.child(CommonConstants.ACTIVE_BATTING_TEAM_FIREBASE_REFERENCE)
//                .setValue(activeBattingTeam)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        navigateToScoringScreen();
                    }
                });

    }

    private void setupTossButtonOnClickListener() {
        TextView tvTossButton = (TextView) findViewById(R.id.toss_button);
        tvTossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double tossValue = Math.random();
                if(tossValue <= CommonConstants.HALF){
                    tvTossButton.setText(teamAName + CommonConstants.WINS_THE_TOSS);
                    activeBattingTeam = CommonConstants.TEAM_A;
                    activeBallingTeam = CommonConstants.TEAM_B;
                }else{
                    tvTossButton.setText(teamBName + CommonConstants.WINS_THE_TOSS);
                    activeBattingTeam = CommonConstants.TEAM_B;
                    activeBallingTeam = CommonConstants.TEAM_A;
                }

                LinearLayout llWinningTeamPreference = (LinearLayout) findViewById(R.id.playing_choice_selector);
                llWinningTeamPreference.setVisibility(View.VISIBLE);
            }
        });
    }
}