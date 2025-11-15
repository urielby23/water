package com.tecmilenio.waterreminderapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.tecmilenio.waterreminderapp.databinding.FragmentSettingsBinding;
import com.tecmilenio.waterreminderapp.utils.AlarmScheduler;
import com.tecmilenio.waterreminderapp.utils.NotificationHelper;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        // --- PERMISOS ANDROID 13+ ---
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                        10
                );
            }
        }

        // --- SHAREDPREFERENCES ---
        SharedPreferences prefs =
                requireActivity().getSharedPreferences("config", Context.MODE_PRIVATE);

        // -------------------------------
        //   BOTÓN PARA PROBAR NOTIFICACIÓN
        // -------------------------------
        binding.btnReminder.setOnClickListener(v -> {
            NotificationHelper.createChannel(requireContext());
            AlarmScheduler.schedule(requireContext());
        });

        // -------------------------------
        //   SEEK BAR (META DIARIA)
        // -------------------------------
        int metaActual = prefs.getInt("meta_diaria", 2000);

        binding.seekMeta.setProgress(metaActual);
        binding.tvMetaValor.setText(metaActual + " ml");

        binding.seekMeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.tvMetaValor.setText(progress + " ml");
                prefs.edit().putInt("meta_diaria", progress).apply();
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // -------------------------------
        //   CHECKBOX RECORDATORIOS
        // -------------------------------
        boolean recordatoriosActivos =
                prefs.getBoolean("recordatorios_activos", true);

        binding.chkRecordatorios.setChecked(recordatoriosActivos);

        binding.chkRecordatorios.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("recordatorios_activos", isChecked).apply();

            NotificationHelper.createChannel(requireContext());

            if (isChecked) {
                AlarmScheduler.schedule(requireContext());
            } else {
                AlarmScheduler.cancel(requireContext());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 10) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                NotificationHelper.createChannel(requireContext());
                AlarmScheduler.schedule(requireContext());
            }
        }
    }
}
