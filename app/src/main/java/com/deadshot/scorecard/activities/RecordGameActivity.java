package com.deadshot.scorecard.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.R;
import com.deadshot.scorecard.adapters.TeammateAdditionDetailsAdapter;
import com.deadshot.scorecard.models.CricketTeammate;
import com.deadshot.scorecard.models.MatchDetails;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RecordGameActivity extends AppCompatActivity {

    private TeammateAdditionDetailsAdapter teamATeammateAdditionDetailsAdapter;
    private TeammateAdditionDetailsAdapter teamBTeammateAdditionDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_game_activity_layout);

        setupViews();
        configureOnClickListeners();

    }

    private void setupViews() {

        configureTeamATeammatesRecyclerView();
        configureTeamBTeammatesRecyclerView();

    }

    private void configureTeamATeammatesRecyclerView() {

        RecyclerView rvTeammateAdditionDetails = (RecyclerView) findViewById(R.id.team_a_teammates);
        rvTeammateAdditionDetails.setNestedScrollingEnabled(false);
        ArrayList<CricketTeammate> teamAPlayerList = new ArrayList<>();

        teamATeammateAdditionDetailsAdapter = new TeammateAdditionDetailsAdapter(teamAPlayerList);
        rvTeammateAdditionDetails.setAdapter(teamATeammateAdditionDetailsAdapter);
        rvTeammateAdditionDetails.setLayoutManager(new LinearLayoutManager(this));

    }

    private void configureTeamBTeammatesRecyclerView() {

        RecyclerView rvTeammateAdditionDetails = (RecyclerView) findViewById(R.id.team_b_teammates);
        rvTeammateAdditionDetails.setNestedScrollingEnabled(false);
        ArrayList<CricketTeammate> teamBPlayerList = new ArrayList<>();

        teamBTeammateAdditionDetailsAdapter = new TeammateAdditionDetailsAdapter(teamBPlayerList);
        rvTeammateAdditionDetails.setAdapter(teamBTeammateAdditionDetailsAdapter);
        rvTeammateAdditionDetails.setLayoutManager(new LinearLayoutManager(this));

    }

    private void configureOnClickListeners(){
        configureAddTeamATeammateListeners();
        configureAddTeamBTeammateListeners();
        configureStartMatchListener();
    }

    private void configureStartMatchListener() {

        Button bnStartMatch = (Button) findViewById(R.id.start_game_button);
        EditText etTeamAName = (EditText) findViewById(R.id.team_a_name);
        EditText etTeamBName = (EditText) findViewById(R.id.team_b_name);
        EditText etDateOfMatch = (EditText) findViewById(R.id.match_date);

        bnStartMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String groupName = CommonConstants.CITI_GROUP_NAME;
                String teamAName = etTeamAName.getText().toString();
                String teamBName = etTeamBName.getText().toString();
                String dateOfMatch = etDateOfMatch.getText().toString();
                String dateOfMatchKey = dateOfMatch.replaceAll("/", "");
                List<CricketTeammate> teamATeammates = teamATeammateAdditionDetailsAdapter.getAdapterData();
                List<CricketTeammate> teamBTeammates = teamBTeammateAdditionDetailsAdapter.getAdapterData();

//                TODO: Remove after having toss
                CricketTeammate activeBatsmanA = teamATeammates.get(0);
                activeBatsmanA.setActiveBatsman(true);
                teamATeammates.set(0, activeBatsmanA);
                CricketTeammate activeBatsmanB = teamATeammates.get(1);
                activeBatsmanB.setActiveBatsman(true);
                teamATeammates.set(1, activeBatsmanB);
                CricketTeammate activeBaller = teamBTeammates.get(0);
                activeBaller.setActiveBaller(true);
                teamBTeammates.set(0, activeBaller);

                MatchDetails matchDetails = new MatchDetails();
                matchDetails.setTeamAName(teamAName);
                matchDetails.setTeamBName(teamBName);
                matchDetails.setDateOfMatch(dateOfMatch);
                matchDetails.setTeamATeammates(teamATeammates);
                matchDetails.setTeamBTeammates(teamBTeammates);

//                TODO: Shift to the stage of having toss.

                matchDetails.setActiveBattingTeam(CommonConstants.TEAM_A);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                        .getReference(CommonConstants.GAME_DATABASE);

                databaseReference.child(CommonConstants.CRICKET_DATABASE)
                        .child(groupName)
                        .child(dateOfMatchKey)
                        .push()
                        .setValue(matchDetails, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if (error == null) {
                                    // Data was written successfully
                                    String path = ref.getPath().toString();
                                    Log.d(CommonConstants.INFO_LOG_TAG, "Data written to path: " + path);
                                    Intent intent = new Intent(RecordGameActivity.this, ScoringScreenActivity.class);
                                    intent.putExtra(CommonConstants.MATCH_DETAILS_RECORD_DETAILS_REFERENCE, path);
                                    startActivity(intent);
                                } else {
                                    // An error occurred while writing the data
                                    Log.e(CommonConstants.ERROR_LOG_TAG, "Error writing data: " + error.getMessage());
                                }
                            }
                        });

//                saveNewGameInfo(groupName, teamAName, teamBName, dateOfMatch, teamATeammates, teamBTeammates);

            }

    });

    }

    private void configureAddTeamATeammateListeners() {

        Button addTeamATeammatesButton = findViewById(R.id.add_team_a_teammates);

        addTeamATeammatesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText tvNewTeammateName = (EditText) findViewById(R.id.new_team_a_teammate);
                String playerName = tvNewTeammateName.getText().toString();
                tvNewTeammateName.setText(CommonConstants.EMPTY_STRING);
                CricketTeammate teammateDetails = new CricketTeammate();
                teammateDetails.setPlayerName(playerName);

                teamATeammateAdditionDetailsAdapter.addAdapterData(teammateDetails);
                teamATeammateAdditionDetailsAdapter.notifyItemInserted(teamATeammateAdditionDetailsAdapter.getItemCount());

            }
        });

    }

    private void configureAddTeamBTeammateListeners() {

        Button addTeamBTeammatesButton = findViewById(R.id.add_team_b_teammates);

        addTeamBTeammatesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText tvNewTeammateName = (EditText) findViewById(R.id.new_team_b_teammate);
                String playerName = tvNewTeammateName.getText().toString();
                tvNewTeammateName.setText(CommonConstants.EMPTY_STRING);
                CricketTeammate teammateDetails = new CricketTeammate();
                teammateDetails.setPlayerName(playerName);

                teamBTeammateAdditionDetailsAdapter.addAdapterData(teammateDetails);
                teamBTeammateAdditionDetailsAdapter.notifyItemInserted(teamBTeammateAdditionDetailsAdapter.getItemCount());

            }
        });

    }


}