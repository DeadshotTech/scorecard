package com.deadshot.scorecard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.deadshot.scorecard.R;
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
        holder.tvPlayerName.setText(mData.get(position).getPlayerName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPlayerName;
        Button bnEditPlayer;
        Button bnDeletePlayer;
        Button editPlayer;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPlayerName = itemView.findViewById(R.id.teammate_addition_details_player_name);
            bnEditPlayer = itemView.findViewById(R.id.teammate_addition_details_edit_player);
            bnDeletePlayer = itemView.findViewById(R.id.teammate_addition_details_delete_player);
        }
    }
}
