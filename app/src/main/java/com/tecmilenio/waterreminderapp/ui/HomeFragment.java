package com.tecmilenio.waterreminderapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.viewmodel.WaterViewModel;

public class HomeFragment extends Fragment {

    private WaterViewModel viewModel;
    private TextView tvDailyWater;
    private ProgressBar progressBar;
    private int dailyGoal;
    private MediaPlayer drinkSound;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvDailyWater = view.findViewById(R.id.tv_daily_water);
        progressBar = view.findViewById(R.id.progress_daily);

        // Inicializar sonido (USAR requireActivity())
        drinkSound = MediaPlayer.create(requireActivity(), R.raw.beber);

        // Leer meta diaria guardada
        SharedPreferences prefs = requireActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        dailyGoal = prefs.getInt("meta_diaria", 2000);
        progressBar.setMax(dailyGoal);

        viewModel = new ViewModelProvider(this).get(WaterViewModel.class);

        // Actualizar consumo actual
        viewModel.getTodayTotal().observe(getViewLifecycleOwner(), total -> {
            int current = (total != null) ? total : 0;

            tvDailyWater.setText(current + " ml / " + dailyGoal + " ml");
            progressBar.setProgress(current);

            if (current >= dailyGoal) {
                tvDailyWater.setText("Meta cumplida! 🎉\n" + current + " ml / " + dailyGoal + " ml");
            }
        });

        // Botones
        Button btnShare = view.findViewById(R.id.btn_share);
        btnShare.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT,
                    "Hoy llevé un consumo de agua saludable con la Water Reminder App 💧");
            startActivity(Intent.createChooser(i, "Compartir progreso"));
        });

        Button btnEmail = view.findViewById(R.id.btn_email);
        btnEmail.setOnClickListener(v -> {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.setType("message/rfc822");
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"correo@ejemplo.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "Mi progreso diario de agua");
            email.putExtra(Intent.EXTRA_TEXT, "Aquí está mi progreso del día.");
            startActivity(Intent.createChooser(email, "Enviar correo"));
        });

        Button btnCamera = view.findViewById(R.id.btn_camera);
        btnCamera.setOnClickListener(v ->
                startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        );

        Button btnWeb = view.findViewById(R.id.btn_web);
        btnWeb.setOnClickListener(v -> {
            Uri url = Uri.parse("https://www.gob.mx/salud/articulos/la-importancia-de-una-buena-hidratacion");
            Intent i = new Intent(Intent.ACTION_VIEW, url);
            startActivity(i);
        });

        Button btnSettings = view.findViewById(R.id.btn_system_settings);
        btnSettings.setOnClickListener(v -> {
            Intent i = new Intent(android.provider.Settings.ACTION_SETTINGS);
            startActivity(i);
        });

        // FABs
        FloatingActionButton fabGlass = view.findViewById(R.id.fab_add_glass);
        FloatingActionButton fabBottle = view.findViewById(R.id.fab_add_bottle);

        // Vaso
        fabGlass.setOnClickListener(v -> {
            viewModel.insertIntake(250, "Vaso");

            // Reproducir sonido siempre
            if (drinkSound != null) {
                if (drinkSound.isPlaying()) {
                    drinkSound.seekTo(0);
                }
                drinkSound.start();
            }
        });

        // Botella
        fabBottle.setOnClickListener(v -> {
            viewModel.insertIntake(300, "Botella");

            if (drinkSound != null) {
                if (drinkSound.isPlaying()) {
                    drinkSound.seekTo(0);
                }
                drinkSound.start();
            }
        });

        // Mensaje retardado
        new Handler().postDelayed(() ->
                        Toast.makeText(getContext(), "Actualizando datos...", Toast.LENGTH_SHORT).show(),
                3000);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (drinkSound != null) {
            drinkSound.release();
            drinkSound = null;
        }
    }
}
