package com.tecmilenio.waterreminderapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.databinding.FragmentTipsBinding;
import com.tecmilenio.waterreminderapp.ui.adapters.TipsAdapter;

import java.util.Arrays;
import java.util.List;

public class TipsFragment extends Fragment {

    private FragmentTipsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTipsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        RecyclerView rv = binding.rvTips;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> tips = Arrays.asList(
                getResources().getStringArray(R.array.tips_list)
        );


        rv.setAdapter(new TipsAdapter(tips));

        return view;
    }
}
