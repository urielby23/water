package com.tecmilenio.waterreminderapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tecmilenio.waterreminderapp.data.WaterDatabase;
import com.tecmilenio.waterreminderapp.data.dao.WaterIntakeDao;
import com.tecmilenio.waterreminderapp.data.entities.DaySummary;
import com.tecmilenio.waterreminderapp.data.entities.WaterIntake;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaterViewModel extends AndroidViewModel {

    private final WaterIntakeDao dao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public WaterViewModel(@NonNull Application application) {
        super(application);
        dao = WaterDatabase.getDatabase(application).waterIntakeDao();
    }

    public LiveData<List<WaterIntake>> getAllIntakes() {
        return dao.getAllIntakes();
    }

    public LiveData<Integer> getTodayTotal() {
        String fechaHoy = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        return dao.getTotalForDate(fechaHoy);
    }

    public void insertIntake(int cantidad, String tipoRecipiente) {
        executor.execute(() -> {
            String fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            String hora = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
            dao.insert(new WaterIntake(fecha, hora, cantidad, tipoRecipiente));
        });
    }

    public LiveData<List<WaterIntake>> getIntakesByDate(String fecha) {
        return dao.getIntakesByDate(fecha);
    }

    public LiveData<List<DaySummary>> getDailySummary() {
        return dao.getDailySummary();
    }
}
