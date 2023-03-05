package com.deadshot.scorecard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.deadshot.scorecard.utilities.BatsmanUtility;
import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.R;
import com.deadshot.scorecard.models.CricketTeammate;

import java.util.List;

public class PlayerBattingScoreCardAdapter extends RecyclerView.Adapter<PlayerBattingScoreCardAdapter.ViewHolder> {
    private List<CricketTeammate> mData;

    public PlayerBattingScoreCardAdapter(List<CricketTeammate> data) {
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
    public PlayerBattingScoreCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.player_batting_scorecard_layout, parent, false);
        return new PlayerBattingScoreCardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerBattingScoreCardAdapter.ViewHolder holder, int position) {

        CricketTeammate playerInfo = mData.get(position);
        String strikeRate = BatsmanUtility.getBatsmanStrikeRate(playerInfo);

        holder.tvPlayerName.setText(playerInfo.getPlayerName());
        holder.tvPlayerRuns.setText(playerInfo.getRunsScored() + CommonConstants.EMPTY_STRING);
        holder.tvPlayerBalls.setText(playerInfo.getBallsPlayed() + CommonConstants.EMPTY_STRING);
        holder.tvPlayerFours.setText(playerInfo.getFoursScored() + CommonConstants.EMPTY_STRING);
        holder.tvPlayerSixes.setText(playerInfo.getSixesScored() + CommonConstants.EMPTY_STRING);
        holder.tvPlayerStrikeRate.setText(strikeRate);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPlayerName;
        TextView tvPlayerRuns;
        TextView tvPlayerBalls;
        TextView tvPlayerSixes;
        TextView tvPlayerFours;
        TextView tvPlayerStrikeRate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPlayerName = itemView.findViewById(R.id.player_scorecard_name);
            tvPlayerRuns = itemView.findViewById(R.id.player_scorecard_runs);
            tvPlayerBalls = itemView.findViewById(R.id.player_scorecard_balls);
            tvPlayerFours = itemView.findViewById(R.id.player_scorecard_fours);
            tvPlayerSixes = itemView.findViewById(R.id.player_scorecard_sixes);
            tvPlayerStrikeRate = itemView.findViewById(R.id.player_scorecard_strike_rate);
        }
    }
}

