package com.deadshot.scorecard.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.R;
import com.deadshot.scorecard.adapters.MatchStatisticsSummaryAdapter;
import com.deadshot.scorecard.models.MatchDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllMatchesListActivity extends AppCompatActivity {

    private MatchStatisticsSummaryAdapter matchStatisticsSummaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_matches_list);

        setupViews();
        configureFirebaseDataReadListeners();

    }

    private void configureFirebaseDataReadListeners() {

        configureMatchSummaryFirebaseDataRead();

    }

    private void configureMatchSummaryFirebaseDataRead() {

        String groupName = CommonConstants.CITI_GROUP_NAME;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        database.setPersistenceEnabled(true);
        DatabaseReference databaseReference = database
                .getReference(CommonConstants.GAME_DATABASE)
                .child(CommonConstants.CRICKET_DATABASE)
                .child(groupName);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<MatchDetails> data = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot snap : snapshot.getChildren()){
                        data.add(snap.getValue(MatchDetails.class));
                    }
                }

                matchStatisticsSummaryAdapter.setAdapterData(data);
                matchStatisticsSummaryAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(CommonConstants.WARNING_LOG_TAG, "Failed to read value.", error.toException());
            }
        });


    }

    private void setupViews() {

        configureMatchSummaryRecyclerView();

    }

    private void configureMatchSummaryRecyclerView() {

        RecyclerView rvMatchSummaryList = (RecyclerView) findViewById(R.id.match_summary_list);
        rvMatchSummaryList.setNestedScrollingEnabled(false);
        ArrayList<MatchDetails> matchSummaryList = new ArrayList<>();

        matchStatisticsSummaryAdapter = new MatchStatisticsSummaryAdapter(matchSummaryList);
        rvMatchSummaryList.setAdapter(matchStatisticsSummaryAdapter);
        rvMatchSummaryList.setLayoutManager(new LinearLayoutManager(this));
    }

}