package com.tecmilenio.waterreminderapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tecmilenio.waterreminderapp.data.entities.WaterIntake;

import java.util.List;

@Dao
public interface WaterIntakeDao {

    @Insert
    void insert(WaterIntake intake);

    @Query("SELECT * FROM consumo_agua ORDER BY id DESC")
    LiveData<List<WaterIntake>> getAllIntakes();

    @Query("SELECT SUM(cantidadMl) FROM consumo_agua WHERE fecha = :fecha")
    LiveData<Integer> getTotalForDate(String fecha);
}
