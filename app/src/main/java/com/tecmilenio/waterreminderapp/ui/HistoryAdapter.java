package com.tecmilenio.waterreminderapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.data.entities.WaterIntake;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<WaterIntake> data;

    public HistoryAdapter(List<WaterIntake> data) {
        this.data = data;
    }

    public void updateData(List<WaterIntake> newData) {
        data = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        WaterIntake item = data.get(position);
        holder.text1.setText(item.fecha + " " + item.hora);
        holder.text2.setText(item.cantidadMl + " ml (" + item.tipoRecipiente + ")");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;
        ViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}
