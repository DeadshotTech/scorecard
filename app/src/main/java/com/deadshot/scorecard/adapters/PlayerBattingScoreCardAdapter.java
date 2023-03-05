package com.deadshot.scorecard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.deadshot.scorecard.models.adapter.PlayerScorecard;
import com.deadshot.scorecard.R;

import java.util.List;

public class PlayerBattingScoreCardAdapter extends RecyclerView.Adapter<PlayerBattingScoreCardAdapter.ViewHolder> {
    private List<PlayerScorecard> mData;

    public PlayerBattingScoreCardAdapter(List<PlayerScorecard> data) {
        mData = data;
    }

    public void addAdapterData(PlayerScorecard data){
        mData.add(data);
    }

    public List<PlayerScorecard> getAdapterData(){
        return mData;
    }

    public void setAdapterData(List<PlayerScorecard> data){
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

        PlayerScorecard playerInfo = mData.get(position);

        holder.tvPlayerName.setText(playerInfo.getPlayerName());
        holder.tvPlayerRuns.setText(playerInfo.getBatsmanRuns());
        holder.tvPlayerBalls.setText(playerInfo.getBatsmanBallsPlayed());
        holder.tvPlayerFours.setText(playerInfo.getBatsmanFoursScored());
        holder.tvPlayerSixes.setText(playerInfo.getBatsmanSixesScored());
        holder.tvPlayerStrikeRate.setText(playerInfo.getBatsmanStrikeRate());

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

