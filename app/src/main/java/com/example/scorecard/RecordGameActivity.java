package com.example.scorecard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.scorecard.adapters.TeammateAdditionDetailsAdapter;
import com.example.scorecard.models.CricketTeammate;
import com.example.scorecard.models.MatchDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                MatchDetails matchDetails = new MatchDetails(teamAName, teamBName,
                        dateOfMatch, 0, 0, 0, 0, teamATeammates, teamBTeammates);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                        .getReference(CommonConstants.GAME_DATABASE);

                databaseReference.child(CommonConstants.CRICKET_DATABASE)
                        .child(groupName)
                        .child(dateOfMatchKey)
                        .push()
                        .setValue(matchDetails);

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
                CricketTeammate teammateDetails = new CricketTeammate(playerName);

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
                CricketTeammate teammateDetails = new CricketTeammate(playerName);

                teamBTeammateAdditionDetailsAdapter.addAdapterData(teammateDetails);
                teamBTeammateAdditionDetailsAdapter.notifyItemInserted(teamBTeammateAdditionDetailsAdapter.getItemCount());

            }
        });

    }


}