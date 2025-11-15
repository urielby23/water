package com.tecmilenio.waterreminderapp.ui;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tecmilenio.waterreminderapp.R;

public class AchievementDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_achievement_detail, container, false);

        TextView tv = v.findViewById(R.id.tv_logro_detalle);
        if (getArguments() != null) {
            tv.setText(getArguments().getString("logro"));
        }

        return v;
    }
}
