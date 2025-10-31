package com.tecmilenio.waterreminderapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.viewmodel.WaterViewModel;

public class HomeFragment extends Fragment {

    private WaterViewModel viewModel;
    private TextView tvDailyWater;
    private ProgressBar progressBar;
    private int dailyGoal = 2000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvDailyWater = view.findViewById(R.id.tv_daily_water);
        progressBar = view.findViewById(R.id.progress_daily);
        Button btnAdd = view.findViewById(R.id.btn_add_glass);

        viewModel = new ViewModelProvider(this).get(WaterViewModel.class);

        // Actualizar en tiempo real el total diario
        viewModel.getTodayTotal().observe(getViewLifecycleOwner(), total -> {
            int current = (total != null) ? total : 0;
            tvDailyWater.setText(current + " ml / " + dailyGoal + " ml");
            progressBar.setProgress(current);
        });

        // Registrar nuevo consumo
        btnAdd.setOnClickListener(v -> viewModel.insertIntake(250, "Vaso"));

        return view;
    }
}
