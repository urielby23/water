package com.tecmilenio.waterreminderapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tecmilenio.waterreminderapp.R;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    private final List<String> tips;
    private final int[] images = {
            R.drawable.tip1,
            R.drawable.tip2,
            R.drawable.tip3,
            R.drawable.tip4
    };

    public TipsAdapter(List<String> tips) {
        this.tips = tips;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTip.setText(tips.get(position));
        holder.imgTip.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTip;
        ImageView imgTip;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTip = itemView.findViewById(R.id.tvTip);
            imgTip = itemView.findViewById(R.id.imgTip);
        }
    }
}
