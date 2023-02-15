package com.example.scorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.scorecard.models.MatchDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScoringScreenActicity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring_screen_acticity);

        String matchDetailsReferencePath = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            matchDetailsReferencePath = extras.get(CommonConstants.MATCH_DETAILS_RECORD_DETAILS_REFERENCE).toString();
        }

        configureFirebaseDataReadListeners(matchDetailsReferencePath);
    }

    private void configureFirebaseDataReadListeners(String matchDetailsReferencePath) {

        configureMatchSummaryFirebaseDataRead(matchDetailsReferencePath);

    }

    private void configureMatchSummaryFirebaseDataRead(String matchDetailsReferencePath) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database
                .getReference(matchDetailsReferencePath);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                MatchDetails data;

                data = dataSnapshot.getValue(MatchDetails.class);
                Log.d(CommonConstants.INFO_LOG_TAG, data.toString());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(CommonConstants.WARNING_LOG_TAG, "Failed to read value.", error.toException());
            }
        });


    }
}