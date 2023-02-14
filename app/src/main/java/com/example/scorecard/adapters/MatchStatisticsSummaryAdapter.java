package com.example.scorecard.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scorecard.CommonConstants;
import com.example.scorecard.R;
import com.example.scorecard.ScorecardActivity;
import com.example.scorecard.models.MatchDetails;

import java.util.List;

public class MatchStatisticsSummaryAdapter extends RecyclerView.Adapter<MatchStatisticsSummaryAdapter.ViewHolder> {
    private List<MatchDetails> mData;
    private Context context;

    public MatchStatisticsSummaryAdapter(List<MatchDetails> data){
        mData = data;
    }
    public void addAdapterData(MatchDetails data){
        mData.add(data);
    }

    public List<MatchDetails> getAdapterData(){
        return mData;
    }

    public void setAdapterData(List<MatchDetails> matchDetails){
        mData = matchDetails;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.match_summary_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String teamAScore = mData.get(position).getTeamARuns() +
                CommonConstants.SCORE_SEPERATOR +
                mData.get(position).getTeamAWickets();
        String teamBScore = mData.get(position).getTeamBRuns() +
                CommonConstants.SCORE_SEPERATOR +
                mData.get(position).getTeamBWickets();

        holder.tvDateOfMatch.setText(mData.get(position).getDateOfMatch());
        holder.tvTeamAName.setText(mData.get(position).getTeamAName());
        holder.tvTeamBName.setText(mData.get(position).getTeamBName());
        holder.tvTeamAScore.setText(teamAScore);
        holder.tvTeamBScore.setText(teamBScore);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ScorecardActivity.class);
                int adapterPosition = holder.getAdapterPosition();
                intent.putExtra(CommonConstants.MATCH_DETAILS, mData.get(adapterPosition));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDateOfMatch;
        TextView tvTeamAName;
        TextView tvTeamBName;
        TextView tvTeamAScore;
        TextView tvTeamBScore;

        public ViewHolder(View itemView) {

            super(itemView);
            tvDateOfMatch = itemView.findViewById(R.id.match_summary_match_date);
            tvTeamAName = itemView.findViewById(R.id.match_summary_team_a_name);
            tvTeamBName = itemView.findViewById(R.id.match_summary_team_b_name);
            tvTeamAScore = itemView.findViewById(R.id.match_summary_team_a_score);
            tvTeamBScore = itemView.findViewById(R.id.match_summary_team_b_score);

        }
    }
}
