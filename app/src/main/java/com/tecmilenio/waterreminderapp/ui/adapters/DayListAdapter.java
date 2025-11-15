package com.tecmilenio.waterreminderapp.ui.adapters;

import android.view.*;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.data.entities.DaySummary;

import java.util.List;

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.ViewHolder> {

    private List<DaySummary> list;
    private final OnDayClickListener listener;

    public interface OnDayClickListener {
        void onClick(String fecha);
    }

    public DayListAdapter(List<DaySummary> list, OnDayClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public void updateList(List<DaySummary> newList) {
        list = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DayListAdapter.ViewHolder holder, int position) {
        DaySummary item = list.get(position);

        holder.tvFecha.setText(item.fecha);
        holder.tvTotal.setText(item.total + " ml");
        holder.btnVerDetalles.setOnClickListener(v -> listener.onClick(item.fecha));
    }
    @Override
    public int getItemCount() { return list.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFecha, tvTotal;
        Button btnVerDetalles;

        ViewHolder(View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tv_fecha_dia);
            tvTotal = itemView.findViewById(R.id.tv_total_dia);
            btnVerDetalles = itemView.findViewById(R.id.btn_ver_detalles);
        }
    }

}
