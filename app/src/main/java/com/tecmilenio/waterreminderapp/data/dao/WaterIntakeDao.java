package com.tecmilenio.waterreminderapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tecmilenio.waterreminderapp.data.entities.WaterIntake;
import com.tecmilenio.waterreminderapp.data.entities.DaySummary; // ← AGREGA ESTA IMPORTACIÓN

import java.util.List;

@Dao
public interface WaterIntakeDao {

    @Insert
    void insert(WaterIntake intake);

    @Query("SELECT * FROM consumo_agua ORDER BY id DESC")
    LiveData<List<WaterIntake>> getAllIntakes();

    @Query("SELECT SUM(cantidadMl) FROM consumo_agua WHERE fecha = :fecha")
    LiveData<Integer> getTotalForDate(String fecha);



    @Query("SELECT fecha, SUM(cantidadMl) AS total FROM consumo_agua GROUP BY fecha ORDER BY fecha DESC")
    LiveData<List<DaySummary>> getDailySummary();

    @Query("SELECT * FROM consumo_agua WHERE fecha = :fecha ORDER BY hora ASC")
    LiveData<List<WaterIntake>> getIntakesByDate(String fecha);

}
