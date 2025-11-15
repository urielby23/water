package com.tecmilenio.waterreminderapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.ui.adapters.DayListAdapter;
import com.tecmilenio.waterreminderapp.viewmodel.WaterViewModel;

import java.util.ArrayList;

public class DayListFragment extends Fragment {

    private WaterViewModel viewModel;
    private DayListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_list, container, false);
        Log.d("FRAGMENT_TEST", "DayListFragment cargado!");
        RecyclerView rv = view.findViewById(R.id.rv_days);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new DayListAdapter(new ArrayList<>(), fecha -> {
            Bundle bundle = new Bundle();
            bundle.putString("fecha", fecha);

            getParentFragmentManager().setFragmentResult("selected_day", bundle);

            NavHostFragment.findNavController(this)
                    .navigate(R.id.dayDetailFragment, bundle);

        });

        rv.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(WaterViewModel.class);
        viewModel.getDailySummary().observe(getViewLifecycleOwner(), adapter::updateList);

        return view;
    }
}
