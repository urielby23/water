package com.tecmilenio.waterreminderapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.ui.adapters.TipsPagerAdapter;

import java.util.Arrays;
import java.util.List;

public class TipsTabsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tips_tabs, container, false);

        TabLayout tabLayout = v.findViewById(R.id.tabLayout);
        ViewPager2 viewPager = v.findViewById(R.id.viewPager);

        List<String> tipPages = Arrays.asList(
                "Bebe agua al despertar.",
                "Lleva siempre una botella contigo.",
                "Evita bebidas azucaradas.",
                "Configura recordatorios en la app."
        );

        viewPager.setAdapter(new TipsPagerAdapter(tipPages));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
                tab.setText("Tip " + (position + 1))
        ).attach();

        return v;
    }
}
