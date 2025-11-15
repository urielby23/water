package com.tecmilenio.waterreminderapp.ui;

import android.os.Bundle;
import android.view.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.ui.adapters.AchievementAdapter;

import java.util.Arrays;
import java.util.List;

public class AchievementsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_achievements, container, false);

        RecyclerView rv = v.findViewById(R.id.rv_achievements);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> logros = Arrays.asList(
                "Primer día completado",
                "Meta semanal",
                "Meta mensual"
        );

        rv.setAdapter(new AchievementAdapter(logros, logro -> {
            Bundle b = new Bundle();
            b.putString("logro", logro);

            AchievementDetailFragment f = new AchievementDetailFragment();
            f.setArguments(b);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, f)
                    .addToBackStack(null)
                    .commit();
        }));

        return v;
    }
}
