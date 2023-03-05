package com.deadshot.scorecard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.R;
import com.deadshot.scorecard.models.CricketTeammate;
import com.deadshot.scorecard.utilities.BowlerUtility;

import java.util.List;

public class PlayerBowlingScoreCardAdapter extends RecyclerView.Adapter<PlayerBowlingScoreCardAdapter.ViewHolder> {
    private List<CricketTeammate> mData;

    public PlayerBowlingScoreCardAdapter(List<CricketTeammate> data) {
        mData = data;
    }

    public void addAdapterData(CricketTeammate data){
        mData.add(data);
    }

    public List<CricketTeammate> getAdapterData(){
        return mData;
    }

    public void setAdapterData(List<CricketTeammate> data){
        mData = data;
    }

    @Override
    public PlayerBowlingScoreCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.player_batting_scorecard_layout, parent, false);
        return new PlayerBowlingScoreCardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerBowlingScoreCardAdapter.ViewHolder holder, int position) {

        CricketTeammate playerInfo = mData.get(position);
        String economy = BowlerUtility.getBowlerEconomy(playerInfo);
        String overs = BowlerUtility.getBowlerOvers(playerInfo);

        holder.tvPlayerName.setText(playerInfo.getPlayerName());
        holder.tvPlayerOvers.setText(overs);
        holder.tvPlayerMaidens.setText(playerInfo.getMaidensConceded() + CommonConstants.EMPTY_STRING);
        holder.tvPlayerRunsConceded.setText(playerInfo.getRunsConceded() + CommonConstants.EMPTY_STRING);
        holder.tvPlayerWickets.setText(playerInfo.getWicketsTaken() + CommonConstants.EMPTY_STRING);
        holder.tvPlayerEconomy.setText(economy);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPlayerName;
        TextView tvPlayerOvers;
        TextView tvPlayerMaidens;
        TextView tvPlayerRunsConceded;
        TextView tvPlayerWickets;
        TextView tvPlayerEconomy;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPlayerName = itemView.findViewById(R.id.player_scorecard_name);
            tvPlayerOvers = itemView.findViewById(R.id.player_scorecard_runs);
            tvPlayerMaidens = itemView.findViewById(R.id.player_scorecard_balls);
            tvPlayerRunsConceded = itemView.findViewById(R.id.player_scorecard_fours);
            tvPlayerWickets = itemView.findViewById(R.id.player_scorecard_sixes);
            tvPlayerEconomy = itemView.findViewById(R.id.player_scorecard_strike_rate);
        }
    }
}

