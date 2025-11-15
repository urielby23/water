package com.tecmilenio.waterreminderapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tecmilenio.waterreminderapp.R;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    private final List<String> tips;

    public TipsAdapter(List<String> tips) {
        this.tips = tips;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTip.setText(tips.get(position));
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTip;
        ViewHolder(View itemView) {
            super(itemView);
            tvTip = itemView.findViewById(R.id.tvTip);
        }
    }
}