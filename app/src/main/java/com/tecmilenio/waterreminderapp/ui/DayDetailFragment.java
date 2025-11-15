package com.tecmilenio.waterreminderapp.ui;

import android.os.Bundle;
import android.view.*;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.viewmodel.WaterViewModel;

import java.util.ArrayList;

public class DayDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_detail, container, false);

        Button btnBack = view.findViewById(R.id.btn_back_days);
        btnBack.setOnClickListener(v ->
                NavHostFragment.findNavController(this).popBackStack()
        );

        RecyclerView rv = view.findViewById(R.id.rv_day_detail);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        HistoryAdapter adapter = new HistoryAdapter(new ArrayList<>());
        rv.setAdapter(adapter);

        WaterViewModel viewModel = new ViewModelProvider(this).get(WaterViewModel.class);

        getParentFragmentManager().setFragmentResultListener("selected_day", this, (key, bundle) -> {
            String fecha = bundle.getString("fecha");
            viewModel.getIntakesByDate(fecha).observe(getViewLifecycleOwner(), adapter::updateData);
        });

        return view;
    }
}
