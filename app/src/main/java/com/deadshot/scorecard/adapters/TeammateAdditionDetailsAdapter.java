package com.deadshot.scorecard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.deadshot.scorecard.R;
import com.deadshot.scorecard.constants.CommonConstants;
import com.deadshot.scorecard.models.CricketTeammate;

import java.util.List;

public class TeammateAdditionDetailsAdapter extends RecyclerView.Adapter<TeammateAdditionDetailsAdapter.ViewHolder> {
    private List<CricketTeammate> mData;

    public TeammateAdditionDetailsAdapter(List<CricketTeammate> data) {
        mData = data;
    }

    public void addAdapterData(CricketTeammate data){
        mData.add(data);
    }

    public List<CricketTeammate> getAdapterData(){
        return mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.teammate_addition_details_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvPlayerJerseyNumber.setText(mData.get(position).getJerseyNumber() + CommonConstants.EMPTY_STRING);
        holder.tvPlayerName.setText(mData.get(position).getPlayerName());
        holder.tvPlayerAge.setText(mData.get(position).getAge() + CommonConstants.EMPTY_STRING);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPlayerName;
        TextView tvPlayerJerseyNumber;
        TextView tvPlayerAge;
        ImageView bnDeletePlayer;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPlayerJerseyNumber = (TextView) itemView.findViewById(R.id.teammate_addition_details_player_jersey_number);
            tvPlayerName = (TextView) itemView.findViewById(R.id.teammate_addition_details_player_name);
            tvPlayerAge = (TextView) itemView.findViewById(R.id.teammate_addition_details_player_age);
            bnDeletePlayer = itemView.findViewById(R.id.teammate_addition_details_delete_player);
        }
    }
}
