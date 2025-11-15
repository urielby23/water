package com.tecmilenio.waterreminderapp.ui.adapters;

import android.view.*;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tecmilenio.waterreminderapp.R;

import java.util.List;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder> {

    private final List<String> list;
    private final OnAchievementClick listener;

    public interface OnAchievementClick {
        void onClick(String logro);
    }

    public AchievementAdapter(List<String> list, OnAchievementClick listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String logro = list.get(position);
        holder.text.setText(logro);
        holder.itemView.setOnClickListener(v -> listener.onClick(logro));
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(android.R.id.text1);
        }
    }
}
