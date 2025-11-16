package com.tecmilenio.waterreminderapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tecmilenio.waterreminderapp.R;

import java.util.List;

public class TipsPagerAdapter extends RecyclerView.Adapter<TipsPagerAdapter.TipViewHolder> {

    private final List<String> tips;

    public TipsPagerAdapter(List<String> tips) {
        this.tips = tips;
    }

    @NonNull
    @Override
    public TipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tip_page, parent, false);
        return new TipViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TipViewHolder holder, int position) {
        holder.tvTip.setText(tips.get(position));
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    static class TipViewHolder extends RecyclerView.ViewHolder {
        TextView tvTip;
        TipViewHolder(View itemView) {
            super(itemView);
            tvTip = itemView.findViewById(R.id.tvPageTip);
        }
    }
}
